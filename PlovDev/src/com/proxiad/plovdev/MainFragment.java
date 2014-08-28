package com.proxiad.plovdev;

import com.proxiad.plovdev.adapters.LectureAdapter;
import com.proxiad.plovdev.utils.DataParser;

import android.app.ListFragment;
import android.os.Bundle;

public class MainFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LectureAdapter adapter = new LectureAdapter(getActivity(), DataParser.getLectures());
		setListAdapter(adapter);
	}

}