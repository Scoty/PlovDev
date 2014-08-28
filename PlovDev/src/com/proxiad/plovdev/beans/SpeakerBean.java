package com.proxiad.plovdev.beans;

import java.util.List;

import android.widget.ImageView;

public class SpeakerBean {

	private ImageView portrait;
	private String name;
	private String bio;
	private List<LectureBean> lectures;

	public SpeakerBean() {
		super();
	}
	
	public SpeakerBean(ImageView portrait, String name, String bio, List<LectureBean> lectures) {
		super();
		this.portrait = portrait;
		this.name = name;
		this.bio = bio;
		this.lectures = lectures;
	}

	public ImageView getPortrait() {
		return portrait;
	}

	public void setPortrait(ImageView portrait) {
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
