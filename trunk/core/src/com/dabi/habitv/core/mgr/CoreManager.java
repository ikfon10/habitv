package com.dabi.habitv.core.mgr;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.dabi.habitv.core.config.UserConfig;
import com.dabi.habitv.core.plugin.PluginFactory;
import com.dabi.habitv.core.token.TokenReplacer;
import com.dabi.habitv.core.updater.UpdateManager;
import com.dabi.habitv.framework.plugin.api.downloader.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.api.dto.DownloaderDTO;
import com.dabi.habitv.framework.plugin.api.dto.ExporterDTO;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO.ProtocolEnum;
import com.dabi.habitv.framework.plugin.api.exporter.PluginExporterInterface;
import com.dabi.habitv.framework.plugin.api.provider.PluginProviderInterface;

public final class CoreManager {

	private CategoryManager categoryManager;

	private EpisodeManager episodeManager;

	private final UserConfig config;

	private final Map<String, Integer> taskName2PoolSizeMap;

	private final PluginFactory<PluginProviderInterface> pluginProviderFactory;

	private final UpdateManager updateManager;


	public CoreManager(final UserConfig config) {
		this.config = config;
		pluginProviderFactory = new PluginFactory<>(PluginProviderInterface.class, config.getProviderPluginDir());
		taskName2PoolSizeMap = config.getTaskDefinition();
		TokenReplacer.setCutSize(config.getFileNameCutSize());
		setProxy(config);
		updateManager = new UpdateManager(config.autoriseSnapshot());
	}

	private void setProxy(final UserConfig config) {
		final Map<String, Map<ProtocolEnum, ProxyDTO>> plugin2protocol2proxy = config.getProxy();

		// set the defaut http proxy
		final Map<ProtocolEnum, ProxyDTO> defaultProxyMap = plugin2protocol2proxy.get(null);
		if (defaultProxyMap != null) {
			final ProxyDTO httpProxy = defaultProxyMap.get(ProxyDTO.ProtocolEnum.HTTP);
			if (httpProxy != null) {
				setHttpProxy(httpProxy);
			}
		}

		// set the proxy to each plugin provider
		for (final PluginProviderInterface provider : pluginProviderFactory.getAllPlugin()) {
			Map<ProtocolEnum, ProxyDTO> pluginProxy = plugin2protocol2proxy.get(provider.getName());
			if (pluginProxy == null) {
				pluginProxy = defaultProxyMap;
			}
			provider.setProxy(pluginProxy);
		}
	}

	private void setHttpProxy(final ProxyDTO httpProxy) {
		System.setProperty("http.proxyHost", httpProxy.getHost());
		System.setProperty("http.proxyPort", String.valueOf(httpProxy.getPort()));
	}

	public CategoryManager getCategoryManager() {
		if (categoryManager == null) {
			categoryManager = new CategoryManager(pluginProviderFactory.getAllPlugin(), taskName2PoolSizeMap);
		}
		return categoryManager;
	}

	public EpisodeManager getEpisodeManager() {
		if (episodeManager == null) {
			initEpisodeManager(pluginProviderFactory.getAllPlugin(), taskName2PoolSizeMap);
		}
		return episodeManager;
	}

	private void initEpisodeManager(final Collection<PluginProviderInterface> pluginProviderList, final Map<String, Integer> taskName2PoolSize) {
		// downloaders factory
		final PluginFactory<PluginDownloaderInterface> pluginDownloaderFactory = new PluginFactory<>(PluginDownloaderInterface.class,
				config.getDownloaderPluginDir());
		// map of downloaders by name
		final Map<String, PluginDownloaderInterface> downloaderName2downloader = pluginDownloaderFactory.getAllPluginMap();
		// downloaders bin path
		final Map<String, String> downloaderName2BinPath = config.getDownloader();
		// DL DTO
		final DownloaderDTO downloader = new DownloaderDTO(config.getCmdProcessor(), downloaderName2downloader, downloaderName2BinPath,
				config.getDownloadOuput(), config.getIndexDir());
		// exporters factory
		final PluginFactory<PluginExporterInterface> pluginExporterFactory = new PluginFactory<>(PluginExporterInterface.class, config.getExporterPluginDir());
		// map of exporters by name
		final Map<String, PluginExporterInterface> exporterName2exporter = pluginExporterFactory.getAllPluginMap();
		// export DTO
		final ExporterDTO exporter = new ExporterDTO(exporterName2exporter, config.getExporter());

		// manager
		episodeManager = new EpisodeManager(downloader, exporter, pluginProviderList, taskName2PoolSize, config.getMaxAttempts());
	}

	public void retreiveEpisode(final Map<String, Set<CategoryDTO>> categoriesToGrab) {
		getEpisodeManager().retreiveEpisode(categoriesToGrab);
	}

	public Map<String, Set<CategoryDTO>> findCategory() {
		return getCategoryManager().findCategory();
	}

	public void forceEnd() {
		if (episodeManager != null) {
			episodeManager.forceEnd();
		}
		if (categoryManager != null) {
			categoryManager.forceEnd();
		}
	}

	public void reDoExport() {
		episodeManager.reTryExport();
	}

	public boolean hasExportToResume() {
		return episodeManager.hasExportToResume();
	}

	public void clearExport() {
		episodeManager.clearExport();
	}

	private void reloadPlugin() {
		pluginProviderFactory.loadPlugins();
		getEpisodeManager().setPluginProviderList(pluginProviderFactory.getAllPlugin());
		getCategoryManager().setPluginProviderList(pluginProviderFactory.getAllPlugin());
	}

	public void update() {
		updateManager.process();
		reloadPlugin();
	}

	public UpdateManager getUpdateManager() {
		return updateManager;
	}

}
