package com.proxiad.plovdev.beans;

import java.util.List;

public class SpeakerBean {

	private int portraitId;
	private String name;
	private String bio;
	private List<LectureBean> lectures;

	public SpeakerBean(int portraitId, String name, String bio, List<LectureBean> lectures) {
		super();
		this.portraitId = portraitId;
		this.name = name;
		this.bio = bio;
		this.lectures = lectures;
	}

	public int getPortraitId() {
		return portraitId;
	}

	public void setPortraitId(int portraitId) {
		this.portraitId = portraitId;
	}

	public SpeakerBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<LectureBean> getLectures() {
		return lectures;
	}

	public void setLectures(List<LectureBean> lectures) {
		this.lectures = lectures;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + portraitId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpeakerBean other = (SpeakerBean) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (portraitId != other.portraitId)
			return false;
		return true;
	}
}
