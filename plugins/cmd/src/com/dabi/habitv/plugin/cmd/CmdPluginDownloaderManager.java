package com.dabi.habitv.plugin.cmd;

import java.util.regex.Matcher;

import com.dabi.habitv.api.plugin.api.PluginDownloaderInterface;
import com.dabi.habitv.api.plugin.dto.DownloadParamDTO;
import com.dabi.habitv.api.plugin.exception.DownloadFailedException;
import com.dabi.habitv.api.plugin.exception.ExecutorFailedException;
import com.dabi.habitv.api.plugin.holder.DownloaderPluginHolder;
import com.dabi.habitv.api.plugin.holder.ProcessHolder;
import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.utils.CmdExecutor;

public final class CmdPluginDownloaderManager implements
		PluginDownloaderInterface {

	@Override
	public String getName() {
		return CmdConf.NAME;
	}

	@Override
	public ProcessHolder download(final DownloadParamDTO downloadParam,
			final DownloaderPluginHolder downloaders)
			throws DownloadFailedException {
		final String cmd = downloadParam.getParam(CmdConf.PARAM_KEY);
		if (cmd == null) {
			throw new IllegalArgumentException("cmd parameters must be defined");
		}
		replaceIfContains(cmd, FrameworkConf.DOWNLOAD_INPUT,
				Matcher.quoteReplacement(downloadParam.getDownloadInput()));
		replaceIfContains(cmd, FrameworkConf.DOWNLOAD_DESTINATION,
				Matcher.quoteReplacement(downloadParam.getDownloadOutput()));

		try {
			return new CmdExecutor(downloaders.getCmdProcessor(), cmd,
					CmdConf.MAX_HUNG_TIME);
		} catch (final ExecutorFailedException e) {
			throw new DownloadFailedException(e);
		}
	}

	private String replaceIfContains(final String cmd,
			final String paramPattern, final String param) {
		if (cmd.contains(param)) {
			return cmd.replaceFirst(paramPattern, param);
		} else {
			throw new IllegalArgumentException(
					"cmd param must defined the param pattern " + paramPattern);
		}
	}

	@Override
	public DownloadableState canDownload(final String downloadInput) {
		return DownloadableState.IMPOSSIBLE;
	}

}
