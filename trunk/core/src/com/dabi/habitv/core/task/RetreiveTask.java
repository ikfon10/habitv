package com.dabi.habitv.core.task;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dabi.habitv.core.config.HabitTvConf;
import com.dabi.habitv.core.dao.DownloadedDAO;
import com.dabi.habitv.core.event.EpisodeStateEnum;
import com.dabi.habitv.core.event.RetreiveEvent;
import com.dabi.habitv.core.publisher.Publisher;
import com.dabi.habitv.core.token.TokenReplacer;
import com.dabi.habitv.framework.plugin.api.dto.DownloaderDTO;
import com.dabi.habitv.framework.plugin.api.dto.EpisodeDTO;
import com.dabi.habitv.framework.plugin.api.dto.ExportDTO;
import com.dabi.habitv.framework.plugin.api.dto.ExporterDTO;
import com.dabi.habitv.framework.plugin.api.exporter.PluginExporterInterface;
import com.dabi.habitv.framework.plugin.api.provider.PluginProviderInterface;
import com.dabi.habitv.framework.plugin.exception.InvalidEpisodeException;
import com.dabi.habitv.framework.plugin.exception.TechnicalException;

public class RetreiveTask extends AbstractEpisodeTask {

	private final Publisher<RetreiveEvent> retreivePublisher;

	private final TaskAdder taskAdder;

	private final ExporterDTO exporter;

	private final PluginProviderInterface provider;

	private final DownloaderDTO downloader;

	private final DownloadedDAO downloadDAO;

	public RetreiveTask(final EpisodeDTO episode, final Publisher<RetreiveEvent> publisher, final TaskAdder taskAdder, final ExporterDTO exporter,
			final PluginProviderInterface provider, final DownloaderDTO downloader, final DownloadedDAO downloadDAO) {
		super(episode);
		retreivePublisher = publisher;
		this.taskAdder = taskAdder;
		this.exporter = exporter;
		this.provider = provider;
		this.downloadDAO = downloadDAO;
		this.downloader = downloader;
	}

	@Override
	protected void added() {
		LOG.info("Episode to retreive " + getEpisode());
		retreivePublisher.addNews(new RetreiveEvent(getEpisode(), EpisodeStateEnum.TO_DOWNLOAD));
	}

	@Override
	protected void failed(final Exception e) {
		LOG.error("Episode failed to retreive " + getEpisode(), e);
		// retreivePublisher.addNews(new RetreiveEvent(getEpisode(),
		// EpisodeStateEnum.FAILED, e, "retreive"));
	}

	@Override
	protected void ended() {
		LOG.error("Episode is ready " + getEpisode());
		retreivePublisher.addNews(new RetreiveEvent(getEpisode(), EpisodeStateEnum.READY));
	}

	@Override
	protected void started() {

	}

	@Override
	protected Object doCall() throws InterruptedException, ExecutionException {
		check();
		download();
		export(exporter.getExporterList());
		return null;
	}

	private void export(final List<ExportDTO> exporterList) throws InterruptedException, ExecutionException {
		for (final ExportDTO export : exporterList) {
			if (validCondition(export, getEpisode())) {
				final PluginExporterInterface pluginexporter = exporter.getExporter(export.getName(), HabitTvConf.DEFAULT_EXPORTER);
				final ExportTask exportTask = new ExportTask(getEpisode(), export, pluginexporter, retreivePublisher);
				taskAdder.addExportTask(exportTask, export.getName());
				// wait for the current exportTask before running an other
				exportTask.waitEndOfTreatment();
				// sub export tasks are run asynchronously
				if (!export.getExporter().isEmpty()) {
					export(export.getExporter());
				}
			}
		}
	}

	private boolean validCondition(final ExportDTO export, final EpisodeDTO episode) {
		boolean ret = true;
		if (export.getConditionReference() != null) {
			final String reference = export.getConditionReference();
			final String actualString = TokenReplacer.replaceRef(reference, episode);
			ret = actualString.matches(export.getConditionPattern());
		}
		return ret;
	}

	private void download() {
		final DownloadTask downloadTask = new DownloadTask(getEpisode(), provider, downloader, retreivePublisher, downloadDAO);
		taskAdder.addDownloadTask(downloadTask, getEpisode().getCategory().getChannel());
		downloadTask.waitEndOfTreatment();
		// TODO gérer des exceptions de type downloadfailedException
	}

	private void check() {
		try {
			getEpisode().check();
		} catch (final InvalidEpisodeException e) {
			throw new TechnicalException(e);
		}
	}

	@Override
	public String toString() {
		return "Retreiving" + getEpisode().toString();
	}
}
