package com.dabi.habitv.core.task;

import java.io.File;

import com.dabi.habitv.core.dao.DownloadedDAO;
import com.dabi.habitv.core.event.EpisodeStateEnum;
import com.dabi.habitv.core.event.RetreiveEvent;
import com.dabi.habitv.core.publisher.Publisher;
import com.dabi.habitv.core.token.TokenReplacer;
import com.dabi.habitv.framework.plugin.api.dto.DownloaderDTO;
import com.dabi.habitv.framework.plugin.api.dto.EpisodeDTO;
import com.dabi.habitv.framework.plugin.api.provider.PluginProviderInterface;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;
import com.dabi.habitv.framework.plugin.exception.ExecutorFailedException;
import com.dabi.habitv.framework.plugin.exception.NoSuchDownloaderException;
import com.dabi.habitv.framework.plugin.utils.CmdProgressionListener;

public class DownloadTask extends AbstractEpisodeTask {

	private final PluginProviderInterface provider;

	private final DownloaderDTO downloader;

	private final Publisher<RetreiveEvent> publisher;

	private final DownloadedDAO downloadedDAO;

	public DownloadTask(final EpisodeDTO episode, final PluginProviderInterface provider, final DownloaderDTO downloader,
			final Publisher<RetreiveEvent> publisher, final DownloadedDAO downloadedDAO) {
		super(episode);
		this.provider = provider;
		this.downloader = downloader;
		this.publisher = publisher;
		this.downloadedDAO = downloadedDAO;
	}

	@Override
	protected void added() {
		LOG.info("Waiting for download of " + getEpisode());
	}

	@Override
	protected void failed(final Exception e) {
		LOG.error("Download failed for " + getEpisode(), e);
		if (e instanceof ExecutorFailedException) {
			final ExecutorFailedException executorFailedException = (ExecutorFailedException) e;
			LOG.error("download of " + getEpisode().getCategory() + " - " + getEpisode().getName() + "failed");
			LOG.error("cmd was" + executorFailedException.getCmd());
			LOG.error(executorFailedException.getFullOuput());
		}
		publisher.addNews(new RetreiveEvent(getEpisode(), EpisodeStateEnum.DOWNLOAD_FAILED, e, "download"));
	}

	@Override
	protected void ended() {
		LOG.info("Download of " + getEpisode() + " done");
		downloadedDAO.addDownloadedFiles(getEpisode().getName());
		publisher.addNews(new RetreiveEvent(getEpisode(), EpisodeStateEnum.DOWNLOADED));
	}

	@Override
	protected void started() {
		LOG.info("Download of " + getEpisode() + " is starting");
		publisher.addNews(new RetreiveEvent(getEpisode(), EpisodeStateEnum.DOWNLOADING));
	}

	@Override
	protected Object doCall() throws DownloadFailedException, NoSuchDownloaderException {
		final String outputFilename = TokenReplacer.replaceAll(downloader.getDownloadOutput(), getEpisode());
		final String outputTmpFileName = outputFilename + ".tmp";
		// delete to prevent resuming since most of the download can't resume
		final File outputFile = new File(outputFilename);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		provider.download(outputTmpFileName, downloader, new CmdProgressionListener() {
			@Override
			public void listen(final String progression) {
				publisher.addNews(new RetreiveEvent(getEpisode(), EpisodeStateEnum.DOWNLOADING, progression));
			}
		}, getEpisode());
		(new File(outputTmpFileName)).renameTo(new File(outputFilename));
		return null;
	}

	@Override
	public int hashCode() {
		return getEpisode().hashCode() + downloader.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		boolean ret;
		if (obj instanceof DownloadTask) {
			final DownloadTask downloadTask = (DownloadTask) obj;
			ret = getEpisode().equals(downloadTask.getEpisode());
			if (getCategory() != null) {
				ret = ret && getCategory().equals(downloadTask.getCategory());
			}
		} else {
			ret = false;
		}
		return ret;
	}

	@Override
	public String toString() {
		return "DL" + getEpisode() + " " + provider.getName() + " " + downloader.getDownloadOutput();
	}

}
