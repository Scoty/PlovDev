package com.proxiad.plovdev;

import android.app.ListFragment;
import android.os.Bundle;

import com.proxiad.plovdev.adapters.SpeakerAdapter;
import com.proxiad.plovdev.utils.DataParser;

public class SpeakersFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SpeakerAdapter adapter = new SpeakerAdapter(getActivity(), DataParser.getSpeakers());
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getActivity().getActionBar().setTitle(R.string.speakers);
	    ((MainActivity) getActivity()).getNavigationDrawerFragment().setCurrentPosition(1);
	}

}
