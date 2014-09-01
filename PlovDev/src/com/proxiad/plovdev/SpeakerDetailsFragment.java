package com.proxiad.plovdev;

import android.app.Fragment;

public class SpeakerDetailsFragment extends Fragment {
	
	@Override
	public void onResume() {
	    super.onResume();
	    getActivity().getActionBar().setTitle(R.string.speaker_details);
	}

}
