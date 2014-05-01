package com.dabi.habitv.tray.model;

import com.dabi.habitv.api.plugin.dto.EpisodeDTO;
import com.dabi.habitv.core.event.EpisodeStateEnum;

public class ActionProgress implements Comparable<ActionProgress> {
	private EpisodeStateEnum state;
	private String progress;
	private String info;
	private final EpisodeDTO episode;

	public ActionProgress(final EpisodeStateEnum state, final String progress,
			final String info, final EpisodeDTO episode) {
		super();
		this.state = state;
		this.progress = progress;
		this.info = info;
		this.episode = episode;
	}

	public EpisodeDTO getEpisode() {
		return episode;
	}

	public EpisodeStateEnum getState() {
		return state;
	}

	public void setState(final EpisodeStateEnum state) {
		this.state = state;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(final String progress) {
		this.progress = progress;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(final String info) {
		this.info = info;
	}

	@Override
	public int compareTo(final ActionProgress o) {
		int ret = state.compareTo(o.state);
		if (ret == 0) {
			if (o.info != null && info != null) {
				ret = o.info.compareTo(info);
			}
			if (ret == 0) {
				ret = o.episode.compareTo(episode);
			}
		}
		return ret;
	}

	@Override
	public boolean equals(final Object obj) {
		boolean ret = false;
		if (obj instanceof ActionProgress) {
			ret = getEpisode().equals(((ActionProgress) obj).getEpisode());
		}
		return ret;
	}

	@Override
	public int hashCode() {
		return getEpisode().hashCode();
	}

	@Override
	public String toString() {
		return getEpisode().toString() + " " + getState();
	}

}
