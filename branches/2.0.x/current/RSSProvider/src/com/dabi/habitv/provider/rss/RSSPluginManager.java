package com.dabi.habitv.provider.rss;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.dabi.habitv.framework.plugin.api.CmdProgressionListener;
import com.dabi.habitv.framework.plugin.api.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.PluginProviderInterface;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.api.dto.DownloadersDTO;
import com.dabi.habitv.framework.plugin.api.dto.EpisodeDTO;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;
import com.dabi.habitv.framework.plugin.exception.NoSuchDownloaderException;
import com.dabi.habitv.framework.plugin.utils.FrameworkConf;

public class RSSPluginManager implements PluginProviderInterface {

	private ClassLoader classLoader;

	@Override
	public Set<EpisodeDTO> findEpisode(final CategoryDTO category) {
		return RSSRetriever.findEpisodeByCategory(classLoader, category);
	}

	@Override
	public Set<CategoryDTO> findCategory() {
		return RSSCategoriesFinder.findCategory(classLoader);
	}

	@Override
	public void download(final String downloadOuput, final DownloadersDTO downloaders, final CmdProgressionListener listener, final EpisodeDTO episode)
			throws DownloadFailedException, NoSuchDownloaderException {
		final String downloaderName = RSSConf.DOWNLOADER;
		final PluginDownloaderInterface pluginDownloader = downloaders.getDownloader(downloaderName);

		final Map<String, String> parameters = new HashMap<>(2);
		parameters.put(FrameworkConf.PARAMETER_BIN_PATH, downloaders.getBinPath(downloaderName));

		pluginDownloader.download(episode.getVideoUrl(), downloadOuput, parameters, listener);
	}

	@Override
	public String getName() {
		return RSSConf.NAME;
	}

	@Override
	public void setClassLoader(final ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

}
