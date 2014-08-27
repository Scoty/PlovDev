package com.proxiad.plovdev.beans;

import java.util.List;

import android.graphics.Bitmap;

public class SpeakerBean {

	private Bitmap portrait;
	private String name;
	private String bio;
	private List<LectureBean> lectures;

	public SpeakerBean() {
		super();
	}
	
	public SpeakerBean(Bitmap portrait, String name, String bio, List<LectureBean> lectures) {
		super();
		this.portrait = portrait;
		this.name = name;
		this.bio = bio;
		this.lectures = lectures;
	}

	public Bitmap getPortrait() {
		return portrait;
	}

	public void setPortrait(Bitmap portrait) {
		this.portrait = portrait;
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
