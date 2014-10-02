package com.proxiad.plovdev;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.utils.DataParser;
import com.proxiad.plovdev.utils.ImageUtils;

public class LectureDetailsActivity extends Activity {

	private int position;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			this.position = savedInstanceState.getInt("position");
		} else {
			this.position = getIntent().getIntExtra("position", 0);
		}
		List<LectureBean> lectureList = (ArrayList<LectureBean>) getIntent().getSerializableExtra("lectureList");
		LectureBean lecture;
		if (lectureList != null) {
			lecture = lectureList.get(position);
		} else {
			lecture = DataParser.getLecture(position);
		}

		setContentView(R.layout.activity_lecture_details);

		ImageView speakerImageView = (ImageView) findViewById(R.id.imageSpeakerPortrait);
		TextView nameLectureView = (TextView) findViewById(R.id.nameLecture);
		TextView descLectureView = (TextView) findViewById(R.id.descLecture);

		speakerImageView.setImageDrawable(lecture.getSpeaker().getPortraitDrawable());
		nameLectureView.setText(lecture.getName());
		descLectureView.setText(lecture.getDescription());
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("position", position);
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
