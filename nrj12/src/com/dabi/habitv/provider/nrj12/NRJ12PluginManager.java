package com.dabi.habitv.provider.nrj12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.api.downloader.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.api.dto.EpisodeDTO;
import com.dabi.habitv.framework.plugin.api.provider.BasePluginProvider;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;

import com.dabi.habitv.framework.plugin.exception.TechnicalException;
import com.dabi.habitv.framework.plugin.holder.DownloaderPluginHolder;
import com.dabi.habitv.framework.plugin.utils.CmdProgressionListener;

public class NRJ12PluginManager extends BasePluginProvider {

	@Override
	public String getName() {
		return NRJ12Conf.NAME;
	}

	@Override
	public Set<EpisodeDTO> findEpisode(final CategoryDTO category) {
		final Set<EpisodeDTO> episodes = new HashSet<>();
		final String main_url = NRJ12Conf.HOME_URL + "/replay-4203/collectionvideo/";
		// System.out.println("category_url=" + category_url);
		final org.jsoup.nodes.Document doc = Jsoup.parse(getUrlContent(main_url, NRJ12Conf.ENCODING));
		final String name = category.getName();
		// System.out.println("name='" + name + "'");
		int i = 0;
		while (true) {
			final String id = String.format("liste_%d", i);
			Element element = doc.getElementById(id);
			if (element == null)
				break;
			final Element anchor = element.child(0).child(0);
			String identifier = anchor.attr("title");
			if (identifier == "") {
				identifier = "_other_";
			}
			// System.out.println("identifier='" + identifier + "'");
			if (name.equals(identifier)) {
				Elements select2 = element.getElementsByClass("content");
				if (select2.size() == 1) {
					element = select2.get(0);
				}
				// System.out.println("element" + element.toString());
				select2 = element.select(".titre");
				for (final Element element2 : select2) {
					final Elements anchors = element2.getElementsByTag("a");
					final Element anchor2 = anchors.get(0);
					// System.out.println("anchor2=" + anchor2.toString());
					final String url = anchor2.attr("href");
					// System.out.println("url=" + url);
					final String title = anchor2.ownText().trim();
					// System.out.println("title='" + title + "'");
					episodes.add(new EpisodeDTO(category, title, url));
				}
			}
			i += 1;
		}
		return episodes;
	}

	@Override
	public Set<CategoryDTO> findCategory() {
		// http://www.nrj12.fr/replay-4203/collectionvideo/

		final Set<CategoryDTO> categories = new HashSet<>();

		final String url = NRJ12Conf.HOME_URL + "/replay-4203/collectionvideo/";
		// System.out.println("url=" + url);
		final Document doc = Jsoup.parse(getUrlContent(url), NRJ12Conf.ENCODING);
		// To get the main categories ("Divertissments", "Infos / Magazines",
		// etc.):
		// final Elements select = doc.select(".replay");
		int i = 0;
		while (true) {
			final String id = String.format("liste_%d", i);
			// System.out.println("id=" + id);
			final Element element = doc.getElementById(id);
			if (element == null)
				break;
			// System.out.println("element" + element.toString());
			final Element anchor = element.child(0).child(0);
			// System.out.println("anchor" + anchor.toString());
			final String identifier = anchor.attr("title");
			if (identifier != "") {
				// System.out.println("identifier=" + identifier.toString());
				categories.add(new CategoryDTO(NRJ12Conf.NAME, identifier, identifier, NRJ12Conf.EXTENSION));
			}
			i += 1;
		}
		// Used for other main categories (e.g. "Film/Téléfilm", etc.):
		categories.add(new CategoryDTO(NRJ12Conf.NAME, "_other_", "_other_", NRJ12Conf.EXTENSION));
		return categories;
	}

	private String getDownloader(final String url) {
		String downloaderName;
		if (url.startsWith(NRJ12Conf.RTMPDUMP_PREFIX)) {
			downloaderName = NRJ12Conf.RTMDUMP;
		} else {
			downloaderName = NRJ12Conf.CURL;
		}
		return downloaderName;
	}

	@Override
	public void download(final String downloadOuput, final DownloaderPluginHolder downloaders, final CmdProgressionListener cmdProgressionListener,
			final EpisodeDTO episode) throws DownloadFailedException {
		final String mediaId = findFinalUrl(episode);

		final String videoUrl = buildUrlVideoInfo(mediaId);

		final String downloaderName = getDownloader(videoUrl);
		final PluginDownloaderInterface pluginDownloader = downloaders.getPlugin(downloaderName);

		final Map<String, String> parameters = new HashMap<>(2);
		parameters.put(FrameworkConf.PARAMETER_BIN_PATH, downloaders.getBinPath(downloaderName));
		parameters.put(FrameworkConf.CMD_PROCESSOR, downloaders.getCmdProcessor());

		pluginDownloader.download(videoUrl, downloadOuput, parameters, cmdProgressionListener, getProtocol2proxy());
	}

	private static final Pattern MEDIAID_PATTERN = Pattern.compile("/(\\d*)-minipicto");

	public String findFinalUrl(final EpisodeDTO episode) {
		final String url = NRJ12Conf.HOME_URL + episode.getUrl();
		final String content = getUrlContent(url);
		final String mediaId = findMediaId(content);
		return mediaId;
	}

	public static String buildUrlVideoInfo(final String mediaId) {
		// Live HTTP headers:
		// -
		// http://95.81.147.19/1UU71eAEJMYNKZIWGHWj23fg_7EEGGNwF_uc=/mogador/web/00126166_h264_12.mp4
		// - Referer:
		// http://www.nrj12.fr/swf/player_video/playerNR12.swf?_=0.8726717974570418
		// captvty subprocess:
		// - curl "http://r.nrj.fr/mogador/web/00148519_h264_12.mp4" -C - -L -g
		// -A "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)"
		// -o "..."
		return NRJ12Conf.REPLAY_URL + "/mogador/web/" + mediaId + "_h264_12.mp4";
	}

	private static String findMediaId(final String content) {
		final Matcher matcher = MEDIAID_PATTERN.matcher(content);
		final boolean hasMatched = matcher.find();
		String ret = null;
		if (hasMatched) {
			ret = matcher.group(matcher.groupCount());
		} else {
			throw new TechnicalException("can't find mediaId");
		}
		return ret;
	}
}
