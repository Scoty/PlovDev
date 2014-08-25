package com.proxiad.plovdev.beans;

import java.sql.Timestamp;

public class LectureBean {
	private Timestamp beginHour;
	private Timestamp endHour;
	private String name;
	private String description;
	private SpeakerBean speaker;

	public Timestamp getBeginHour() {
		return beginHour;
	}

	public void setBeginHour(Timestamp beginHour) {
		this.beginHour = beginHour;
	}

	public Timestamp getEndHour() {
		return endHour;
	}

	public void setEndHour(Timestamp endHour) {
		this.endHour = endHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SpeakerBean getSpeaker() {
		return speaker;
	}

	public void setSpeaker(SpeakerBean speaker) {
		this.speaker = speaker;
	}

}
