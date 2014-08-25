package com.proxiad.plovdev.beans;

import java.util.List;

import android.graphics.Bitmap;

public class SpeakerBean {

	private Bitmap speakerPortrait;
	private String name;
	private String bio;
	private List<LectureBean> lectures;

	public Bitmap getSpeakerPortrait() {
		return speakerPortrait;
	}

	public void setSpeakerPortrait(Bitmap speakerPortrait) {
		this.speakerPortrait = speakerPortrait;
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

}
