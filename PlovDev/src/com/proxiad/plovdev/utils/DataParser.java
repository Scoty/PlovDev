package com.proxiad.plovdev.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.beans.SpeakerBean;

public class DataParser {

	public static List<LectureBean> getLectures() {

		Timestamp beginHour = Timestamp.valueOf("2014-08-25 10:00:00:000000000");
		Timestamp endHour = Timestamp.valueOf("2014-08-25 11:00:00:000000000");
		String name = "Introduction to Java";
		String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		//speaker
		String bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		SpeakerBean speaker = new SpeakerBean(null, "Anton Antonov", bio, null);

		LectureBean lec1 = new LectureBean(beginHour, endHour, name, description, speaker);
		LectureBean lec2 = new LectureBean(beginHour, endHour, name, description, speaker);
		LectureBean lec3 = new LectureBean(beginHour, endHour, name, description, speaker);
		LectureBean lec4 = new LectureBean(beginHour, endHour, name, description, speaker);
		LectureBean lec5 = new LectureBean(beginHour, endHour, name, description, speaker);
		LectureBean lec6 = new LectureBean(beginHour, endHour, name, description, speaker);
		List<LectureBean> lectures = new ArrayList<LectureBean>();
		lectures.add(lec1);
		lectures.add(lec2);
		lectures.add(lec3);
		lectures.add(lec4);
		lectures.add(lec5);
		lectures.add(lec6);

		return lectures;
	}

	public static List<SpeakerBean> getSpeakers() {

		SpeakerBean speaker = new SpeakerBean();
		
		return null;
	}
}
