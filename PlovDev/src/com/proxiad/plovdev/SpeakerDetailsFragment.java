package com.proxiad.plovdev;

import android.support.v4.app.Fragment;

public class SpeakerDetailsFragment extends Fragment {
	
	@Override
	public void onResume() {
	    super.onResume();
	    getActivity().getActionBar().setTitle(R.string.speaker_details);
	}

}
