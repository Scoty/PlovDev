package com.proxiad.plovdev;

import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VenueFragment extends Fragment {

	private final LatLng PLOVDEV = new LatLng(42.156460, 24.754438);

	private View rootView;
	private GoogleMap map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (rootView != null) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
				parent.removeView(rootView);
		}
		try {
			rootView = inflater.inflate(R.layout.fragment_venue, container, false);
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.addMarker(new MarkerOptions().position(PLOVDEV).title("PlovDev Location").snippet("бул. ћарица, на около 200м от панаирни€ мост"))
					.showInfoWindow();
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(PLOVDEV, 15));
		} catch (InflateException e) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.addMarker(new MarkerOptions().position(PLOVDEV).title("PlovDev Location").snippet("бул. ћарица, на около 200м от панаирни€ мост"))
					.showInfoWindow();
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(PLOVDEV, 15));
		}
		return rootView;
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getActivity().getActionBar().setTitle(R.string.venue);
	    ((MainActivity) getActivity()).getNavigationDrawerFragment().setCurrentPosition(2);
	}
}
