package com.dabi.habitv.downloader.rtmpdump;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.api.downloader.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO;
import com.dabi.habitv.framework.plugin.api.update.BaseUpdatablePlugin;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;
import com.dabi.habitv.framework.plugin.exception.ExecutorFailedException;
import com.dabi.habitv.framework.plugin.exception.TechnicalException;
import com.dabi.habitv.framework.plugin.utils.CmdExecutor;
import com.dabi.habitv.framework.plugin.utils.CmdProgressionListener;
import com.dabi.habitv.framework.plugin.utils.EmptyProgressionListener;
import com.dabi.habitv.framework.plugin.utils.OSUtils;

public class RtmpDumpPluginManager extends BaseUpdatablePlugin implements
		PluginDownloaderInterface {

	private static final Pattern VERSION_PATTERN = Pattern
			.compile("RTMPDump ([0-57A-Za-z.-]*) .*");

	@Override
	public String getName() {
		return RtmpDumpConf.NAME;
	}

	@Override
	public void download(final String downloadInput,
			final String downloadDestination,
			final Map<String, String> parameters,
			final CmdProgressionListener listener,
			final Map<ProxyDTO.ProtocolEnum, ProxyDTO> proxies)
			throws DownloadFailedException {

		String binParam = getBinParam(parameters);
		String cmd = binParam + " ";
		final String cmdParam = parameters.get(FrameworkConf.PARAMETER_ARGS);
		if (cmdParam == null) {
			cmd += RtmpDumpConf.DUMP_CMD;
		} else {
			cmd += cmdParam;
		}
		cmd = cmd.replaceFirst(FrameworkConf.DOWNLOAD_INPUT, downloadInput);
		cmd = cmd.replaceFirst(FrameworkConf.DOWNLOAD_DESTINATION,
				downloadDestination);
		if (proxies != null) {
			final ProxyDTO sockProxy = proxies.get(ProxyDTO.ProtocolEnum.SOCKS);
			if (sockProxy != null) {
				cmd += " --socks " + sockProxy.getHost() + ":"
						+ sockProxy.getPort();
			}
		}
		try {
			(new RtmpDumpCmdExecutor(
					parameters.get(FrameworkConf.CMD_PROCESSOR), cmd, listener))
					.execute();
		} catch (final ExecutorFailedException e) {
			throw new DownloadFailedException(e);
		}
	}

}
