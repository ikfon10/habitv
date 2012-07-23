package com.dabi.habitv.framework.plugin.api.dto;

import com.dabi.habitv.framework.plugin.exception.InvalidEpisodeException;
import com.dabi.habitv.framework.plugin.utils.CheckUtils;

public class EpisodeDTO implements Comparable<EpisodeDTO> {

	private final CategoryDTO category;

	private final String name;

	private final String videoUrl;

	public EpisodeDTO(final CategoryDTO category, final String name, final String videoUrl) {
		this.category = category;
		this.name = name;
		this.videoUrl = videoUrl;
	}

	public void check() throws InvalidEpisodeException {
		if (!CheckUtils.checkMinSize(name)) {
			throw new InvalidEpisodeException(name, InvalidEpisodeException.CauseField.NAME);
		}
		if (!CheckUtils.checkMinSize(videoUrl)) {
			throw new InvalidEpisodeException(videoUrl, InvalidEpisodeException.CauseField.URL);
		}
	}

	public String getName() {
		return name;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		int ret;
		if (this.videoUrl != null) {
			ret = this.videoUrl.hashCode();
		} else {
			ret = this.category.hashCode() + this.name.hashCode();
		}
		return ret;
	}

	@Override
	public boolean equals(final Object obj) {
		boolean ret = false;
		if (obj instanceof EpisodeDTO) {
			final EpisodeDTO episode = (EpisodeDTO) obj;
			if (this.videoUrl != null && this.videoUrl.equals(episode.videoUrl)) {
				ret = true;
			} else {
				ret = this.category.equals(episode.getCategory()) && episode.getName().equals(this.name);
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	@Override
	public int compareTo(final EpisodeDTO o) {
		int ret = 0;
		if (!this.equals(o)) {
			ret = category.compareTo(o.category);
			if (ret == 0) {
				ret = name.compareTo(o.name);
			}
		}
		return ret;
	}

}