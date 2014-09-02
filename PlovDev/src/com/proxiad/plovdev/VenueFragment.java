package com.proxiad.plovdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VenueFragment extends Fragment {

	private final LatLng PLOVDEV = new LatLng(42.156460, 24.754438);
	private GoogleMap map;
	private SupportMapFragment mMapFragment;
	private CameraPosition cameraPosition;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_venue, container, false);
		mMapFragment = SupportMapFragment.newInstance();
		mMapFragment.setRetainInstance(true);
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.map_container, mMapFragment).commit();
		if (savedInstanceState != null) {
			this.cameraPosition = savedInstanceState.getParcelable("cameraPosition");
		}
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!this.isHidden()) {
			getActivity().getActionBar().setTitle(R.string.speakers);
		}
		map = mMapFragment.getMap();
		if (map != null) {
			map.addMarker(new MarkerOptions().position(PLOVDEV).title("PlovDev Location").snippet("бул. ћарица, на около 200м от панаирни€ мост"))
					.showInfoWindow();
			if (cameraPosition == null) {
				map.animateCamera(CameraUpdateFactory.newLatLngZoom(PLOVDEV, 15));
			} else {
				map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			}

		}
	}

	@Override
	public void onPause() {
		super.onPause();
		cameraPosition = map.getCameraPosition();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable("cameraPosition", cameraPosition);
	}

}
