package com.proxiad.plovdev;

import android.app.ListFragment;
import android.os.Bundle;

import com.proxiad.plovdev.adapters.PartnerAdapter;
import com.proxiad.plovdev.utils.DataParser;

public class PartnersFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		PartnerAdapter adapter = new PartnerAdapter(getActivity(), DataParser.getPartners());
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getActivity().getActionBar().setTitle(R.string.partners);
	    ((MainActivity) getActivity()).getNavigationDrawerFragment().setCurrentPosition(3);
	}
}
