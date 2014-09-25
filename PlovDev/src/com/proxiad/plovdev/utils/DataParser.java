package com.proxiad.plovdev.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.beans.PartnerBean;
import com.proxiad.plovdev.beans.SpeakerBean;

public class DataParser {

	private static final String URL_SPEAKERS = "http://2013.plovdev.com/data/speakers.json";
	private static final String URL_LECTURES = "http://2013.plovdev.com/data/program.json";

	private static boolean isDataParsed;

	private static List<LectureBean> lectures;
	private static List<SpeakerBean> speakers;

	private static void parseData() {

		String speakersJsonString = null;
		try {
			speakersJsonString = new JsonRetriever().execute(URL_SPEAKERS).get();
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
					String speakerId = speakerJson.getString("id");
					// String name = speakerJson.getString("name");
					// String imgUrl = speakerJson.getString("imgUrl");
					String name = "";
					String imgUrl = "";
					// String personalPageUrl =
					// speakerJson.getString("personalPage");
					String personalPageUrl = "";
//					String companyName = speakerJson.getJSONObject("company").getString("name");
//					String companyUrl = speakerJson.getJSONObject("company").getString("url");
					String companyName = "";
					String companyUrl = "";

					String bio = speakerJson.getString("resume");

					SpeakerBean speaker = new SpeakerBean(R.drawable.speaker_anton_antonov, speakerId, name, imgUrl, personalPageUrl, companyName,
							companyUrl, bio, new ArrayList<LectureBean>());
					speakers.add(speaker);

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String lecturesJsonString = null;
		try {
			lecturesJsonString = new JsonRetriever().execute(URL_LECTURES).get();
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
					if (lectureJson.has("speaker")) {
						String startTimeAsString = lectureJson.getString("start");
						String name = lectureJson.getString("title");
						String speakerId = lectureJson.getJSONObject("speaker").getString("id");
						LectureBean lecture = new LectureBean(startTimeAsString, name, speakerId);
						lectures.add(lecture);
					} else {
						// TODO Add the breaks
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// getStringFromJson(""); PARTNERS

		// Log.e("Aaaaa ", lecturesJsonString + speakersJsonString);

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
}

class JsonRetriever extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost(params[0]);
		httppost.setHeader("Content-type", "application/json");

		InputStream inputStream = null;
		String result = null;

		try {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			inputStream = entity.getContent();
			// json is UTF-8 by default
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Exception squish) {
			}
		}
		return result;
	}

}
