package com.proxiad.plovdev.beans;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.sql.Timestamp;

public class LectureBean implements Serializable {

	private static final long serialVersionUID = 4772024428525844855L;
	private Timestamp beginHour;
	private Timestamp endHour;
	private String name;
	private String description;
	private SpeakerBean speaker;

	public LectureBean() {
		super();
	}

	public LectureBean(Timestamp beginHour, Timestamp endHour, String name, String description, SpeakerBean speaker) {
		super();
		this.beginHour = beginHour;
		this.endHour = endHour;
		this.name = name;
		this.description = description;
		this.speaker = speaker;
		// this.speaker.getLectures().add(this); //auto add current lecture to
		// the list! null ?!?
	}

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

	@SuppressLint("DefaultLocale") @SuppressWarnings("deprecation")
	public String getTimePeriodName() {
		String beginHour = String.format("%02d", this.beginHour.getHours());
		String beginMinutes = String.format("%02d", this.beginHour.getMinutes());
		String endHour = String.format("%02d", this.endHour.getHours());
		String endMinutes = String.format("%02d", this.endHour.getMinutes());
		String timePeriodName = "(" + beginHour + ":" + beginMinutes + " - " + endHour + ":" + endMinutes + ") " + this.name;
		return timePeriodName;
	}

}
