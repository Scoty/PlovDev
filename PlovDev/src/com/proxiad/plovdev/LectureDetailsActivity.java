package com.proxiad.plovdev;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;

import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.utils.DataParser;
import com.proxiad.plovdev.utils.ImageUtils;

public class LectureDetailsActivity extends Activity {

	private int position;
	private float rating;
	private RatingBar ratingBar;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			this.position = savedInstanceState.getInt("position");
			this.rating = savedInstanceState.getFloat("rating");
		} else {
			this.position = getIntent().getIntExtra("position", 0);
			this.rating = 0f;
		}
		List<LectureBean> lectureList = (ArrayList<LectureBean>) getIntent().getSerializableExtra("lectureList");
		LectureBean lecture;
		if (lectureList != null) {
			lecture = lectureList.get(position);
		} else {
			lecture = DataParser.getLecture(position);
		}

		setContentView(R.layout.activity_lecture_details);

		TextView timeTextView = (TextView) findViewById(R.id.time);
		ImageView speakerImageView = (ImageView) findViewById(R.id.imageSpeakerPortrait);
		TextView nameLectureView = (TextView) findViewById(R.id.nameLecture);
		ratingBar = ((RatingBar) findViewById(R.id.rating));

		timeTextView.setText(lecture.getStartTimeAsString());
		speakerImageView.setImageDrawable(lecture.getSpeaker().getPortraitDrawable());
		nameLectureView.setText(lecture.getName());
		ratingBar.setRating(rating);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("position", position);
		rating = ratingBar.getRating();
		outState.putFloat("rating", rating);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getActionBar().setTitle(R.string.title_activity_lecture_details);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ImageUtils.cleanupCache();
	}
}
