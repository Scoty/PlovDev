package com.proxiad.plovdev.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.beans.PartnerBean;
import com.proxiad.plovdev.beans.SpeakerBean;

public class DataParser {

	public static Context context;

	private static final String URL_WEB_PAGE = "http://2013.plovdev.com/";
	private static final String URL_SPEAKERS = URL_WEB_PAGE + "data/speakers.json";
	private static final String URL_LECTURES = URL_WEB_PAGE + "data/program.json";

	private static boolean isDataParsed;

	private static List<LectureBean> lectures;
	private static List<SpeakerBean> speakers;

	private static void parseData() {
		// parse speakers json
		String speakersJsonString = null;
		try {
			speakersJsonString = new JsonUtils().execute(URL_SPEAKERS).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONArray speakersJsonArray;
		
		if (speakersJsonString != null) {
			try {
				speakersJsonArray = new JSONArray(speakersJsonString);
				speakers = new ArrayList<SpeakerBean>();
				for (int i = 0; i < speakersJsonArray.length(); i++) {
					JSONObject speakerJson = speakersJsonArray.getJSONObject(i);

					String speakerId = getStringFromJson(speakerJson, "id");
					String name = getStringFromJson(speakerJson, "name");
					String imgUrl = getStringFromJson(speakerJson, "img");
					String personalPageUrl = getStringFromJson(speakerJson, "personalPage");
					String companyName = "";
					String companyUrl = "";
					if (speakerJson.has("company")) {
						JSONObject company = speakerJson.getJSONObject("company");
						companyName = getStringFromJson(company, "name");
						companyUrl = getStringFromJson(company, "url");
					}
					String bio = getStringFromJson(speakerJson, "resume");

					Drawable speakerDrawable = ImageUtils.getCachedDrawable(speakerId);

					if (speakerDrawable == null) {
						Bitmap bitmap = ImageUtils.load(URL_WEB_PAGE + imgUrl).bitmap;
						if (bitmap != null) {
							ImportUtils.addSpeakerPortraitToCache(speakerId, bitmap);
							speakerDrawable = ImageUtils.getCachedDrawable(speakerId);
						} else {
							speakerDrawable = ImageUtils.getDefaultDrawable(context);
						}

					}
					SpeakerBean speaker = new SpeakerBean(speakerId, name, imgUrl, personalPageUrl, companyName, companyUrl, bio,
							new ArrayList<LectureBean>());

					speakers.add(speaker);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// parse lectures json
		String lecturesJsonString = null;
		try {
			lecturesJsonString = new JsonUtils().execute(URL_LECTURES).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONArray lecturesJsonArray;
		if (lecturesJsonString != null) {
			try {
				lecturesJsonArray = new JSONArray(lecturesJsonString);
				lectures = new ArrayList<LectureBean>();
				for (int i = 0; i < lecturesJsonArray.length(); i++) {
					JSONObject lectureJson = lecturesJsonArray.getJSONObject(i);
					String startTimeAsString = getStringFromJson(lectureJson, "start");
					String name = getStringFromJson(lectureJson, "title");
					LectureBean lecture;
					if (lectureJson.has("speaker")) {
						String speakerId = getStringFromJson(lectureJson.getJSONObject("speaker"), "id");
						lecture = new LectureBean(startTimeAsString, name, speakerId);
						lectures.add(lecture);
					} else {
						// String icon = getStringFromJson(lectureJson, "icon");
						// lecture = new LectureBean(startTimeAsString, name,
						// icon);
					}
					// lectures.add(lecture);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// getStringFromJson(""); PARTNERS

		for (LectureBean lecture : lectures) {
			for (SpeakerBean speaker : speakers) {
				if (lecture.getIdSpeaker().equals(speaker.getSpeakerId())) {
					lecture.setSpeaker(speaker);
					speaker.getLectures().add(lecture);
				}
			}
		}
	}

	public static List<LectureBean> getLectures() {
		if (!isDataParsed) {
			parseData();
		}
		return lectures;
	}

	public static List<SpeakerBean> getSpeakers() {
		if (!isDataParsed) {
			parseData();
		}
		return speakers;
	}

	public static List<PartnerBean> getPartners() {
		// Proxiad
		int portraitId = R.drawable.partner_proxiad;
		String urlLink = "http://www.proxiad.com";
		PartnerBean partProxiad = new PartnerBean(portraitId, urlLink);
		// Proxiad Tech Exchange
		portraitId = R.drawable.partner_ptx;
		urlLink = "http://www.proxiad.com";
		PartnerBean partPtx = new PartnerBean(portraitId, urlLink);
		// HacKafe
		portraitId = R.drawable.partner_hackafe;
		urlLink = "http://hackafe.org/";
		PartnerBean partHacKafe = new PartnerBean(portraitId, urlLink);
		// PuSS
		portraitId = R.drawable.partner_pu_ss;
		urlLink = "https://uni-plovdiv.bg/pages/index/16/";
		PartnerBean partPuSS = new PartnerBean(portraitId, urlLink);
		// MediaCafe
		portraitId = R.drawable.partner_media_cafe;
		urlLink = "http://mediacafe.bg/";
		PartnerBean partMediaCafe = new PartnerBean(portraitId, urlLink);

		List<PartnerBean> partners = new ArrayList<PartnerBean>();
		partners.add(partProxiad);
		partners.add(partPtx);
		partners.add(partHacKafe);
		partners.add(partPuSS);
		partners.add(partMediaCafe);

		return partners;
	}

	public static LectureBean getLecture(int location) {
		return lectures.get(location);
	}

	public static SpeakerBean getSpeaker(int location) {
		return speakers.get(location);
	}

	private static String getStringFromJson(JSONObject json, String name) throws JSONException {
		if (json.has(name)) {
			return json.getString(name);
		}
		return "";
	}
}
