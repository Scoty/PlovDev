package com.proxiad.plovdev;

import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.proxiad.plovdev.beans.LectureBean;
import com.proxiad.plovdev.utils.DataParser;

public class LectureDetailsActivity extends Activity {

	private int position;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
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

		speakerImageView.setImageResource(lecture.getSpeaker().getPortraitId());
		nameLectureView.setText(lecture.getName());
		descLectureView.setText(lecture.getDescription());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt("position", position);
	}

	@Override
	public void onResume() {
		super.onResume();
		getActionBar().setTitle(R.string.title_activity_lecture_details);
	}
}
