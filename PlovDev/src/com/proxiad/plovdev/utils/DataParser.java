package com.proxiad.plovdev.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;

import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.beans.PartnerBean;
import com.proxiad.plovdev.beans.SpeakerBean;

public class DataParser {

	private static List<LectureBean> lectures;
	private static List<SpeakerBean> speakers;

	private static void parseData() {
		// this method is always executed at the application init
		Timestamp beginHour = Timestamp.valueOf("2014-08-25 10:00:00");
		Timestamp endHour = Timestamp.valueOf("2014-08-25 11:00:00");
		String name = "Introduction to Java";
		String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		// speaker
		String bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		SpeakerBean speakerAnton = new SpeakerBean(R.drawable.speaker_anton_antonov, "Anton Antonov", bio, new ArrayList<LectureBean>());
		SpeakerBean speakerMark = new SpeakerBean(R.drawable.speaker_mark_rogers, "Mark Rogers", bio, new ArrayList<LectureBean>());
		SpeakerBean speakerZdravko = new SpeakerBean(R.drawable.speaker_zdravko_nestorov, "Zdravko Nestorov", bio, new ArrayList<LectureBean>());
		SpeakerBean speakerGenadi = new SpeakerBean(R.drawable.speaker_genadi_ivanov, "Genadi Ivanov", bio, new ArrayList<LectureBean>());
		speakers = new ArrayList<SpeakerBean>();
		speakers.add(speakerAnton);
		speakers.add(speakerMark);
		speakers.add(speakerZdravko);
		speakers.add(speakerGenadi);

		LectureBean lec1 = new LectureBean(beginHour, endHour, name + " 1", description, speakerAnton);
		LectureBean lec2 = new LectureBean(beginHour, endHour, name + " 2", description, speakerMark);
		LectureBean lec3 = new LectureBean(beginHour, endHour, name + " 3", description, speakerZdravko);
		LectureBean lec4 = new LectureBean(beginHour, endHour, name + " 4", description, speakerGenadi);
		LectureBean lec5 = new LectureBean(beginHour, endHour, name + " 5", description, speakerMark);
		LectureBean lec6 = new LectureBean(beginHour, endHour, name + " 6", description, speakerMark);
		LectureBean lec7 = new LectureBean(beginHour, endHour, name + " 7", description, speakerGenadi);
		LectureBean lec8 = new LectureBean(beginHour, endHour, name + " 8", description, speakerGenadi);
		LectureBean lec9 = new LectureBean(beginHour, endHour, name + " 9", description, speakerMark);
		LectureBean lec10 = new LectureBean(beginHour, endHour, name + " 10", description, speakerMark);
		LectureBean lec11 = new LectureBean(beginHour, endHour, name + " 11", description, speakerZdravko);
		LectureBean lec12 = new LectureBean(beginHour, endHour, name + " 12", description, speakerMark);
		LectureBean lec13 = new LectureBean(beginHour, endHour, name + " 13", description, speakerZdravko);
		LectureBean lec14 = new LectureBean(beginHour, endHour, name + " 14", description, speakerAnton);
		lectures = new ArrayList<LectureBean>();
		lectures.add(lec1);
		lectures.add(lec2);
		lectures.add(lec3);
		lectures.add(lec4);
		lectures.add(lec5);
		lectures.add(lec6);
		lectures.add(lec7);
		lectures.add(lec8);
		lectures.add(lec9);
		lectures.add(lec10);
		lectures.add(lec11);
		lectures.add(lec12);
		lectures.add(lec13);
		lectures.add(lec14);

		for (LectureBean lecture : lectures) {
			for (SpeakerBean speaker : speakers) {
				if (lecture.getSpeaker().equals(speaker)) {
					speaker.getLectures().add(lecture);
				}
			}
		}
	}

	public static List<LectureBean> getLectures() {
		parseData();
		return lectures;
	}

	public static List<SpeakerBean> getSpeakers() {
		parseData();
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
	
	public static Bitmap getRoundedImage(int position, Context context, List<SpeakerBean> itemsArrayList) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), itemsArrayList.get(position).getPortraitId());
		Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

		BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);

		Canvas c = new Canvas(circleBitmap);
		c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);

		return circleBitmap;
	}
	
	public static Bitmap getRoundedImage(Context context, int imageId) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageId);
		Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

		BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);

		Canvas c = new Canvas(circleBitmap);
		c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);

		return circleBitmap;
	} 
}
