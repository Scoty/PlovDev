package com.proxiad.plovdev;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	@SuppressWarnings("unused")
	private Bundle savedInstanceState;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	public NavigationDrawerFragment getNavigationDrawerFragment() {
		return mNavigationDrawerFragment;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.savedInstanceState = savedInstanceState;

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch (position) {
		case 0:
			mTitle = getString(R.string.first_day);
			String mainFragmentTag = "mainFragment";
			Fragment mainFragment = fragmentManager.findFragmentByTag(mainFragmentTag);
			if (mainFragment == null) {
				mainFragment = new MainFragment();
			}
			if (!mainFragment.isAdded()) {
				fragmentManager.beginTransaction().replace(R.id.container, mainFragment, mainFragmentTag).commit();
			}
			break;
		case 1:
			mTitle = getString(R.string.speakers);
			String speakersFragmentTag = "speakersFragment";
			Fragment speakersFragment = fragmentManager.findFragmentByTag(speakersFragmentTag);
			if (speakersFragment == null) {
				speakersFragment = new SpeakersFragment();
				if (!speakersFragment.isAdded()) {
					fragmentManager.beginTransaction().replace(R.id.container, speakersFragment, speakersFragmentTag)
							.addToBackStack(speakersFragmentTag).commit();
				}
			}
			if (!speakersFragment.isAdded()) {
				fragmentManager.beginTransaction().replace(R.id.container, speakersFragment, speakersFragmentTag).commit();
			}
			break;
		case 2:
			mTitle = getString(R.string.venue);
			String venueFragmentTag = "venueFragment";
			Fragment venueFragment = fragmentManager.findFragmentByTag(venueFragmentTag);
			if (venueFragment == null) {
				venueFragment = new VenueFragment();
				if (!venueFragment.isAdded()) {
					fragmentManager.beginTransaction().replace(R.id.container, venueFragment, venueFragmentTag).addToBackStack(venueFragmentTag)
							.commit();
				}
			}
			if (!venueFragment.isAdded()) {
				fragmentManager.beginTransaction().replace(R.id.container, venueFragment, venueFragmentTag).commit();
			}
			break;
		case 3:
			mTitle = getString(R.string.partners);
			String partnersFragmentTag = "partnersFragment";
			Fragment partnersFragment = fragmentManager.findFragmentByTag(partnersFragmentTag);
			if (partnersFragment == null) {
				partnersFragment = new PartnersFragment();
				if (!partnersFragment.isAdded()) {
					fragmentManager.beginTransaction().replace(R.id.container, partnersFragment, partnersFragmentTag)
							.addToBackStack(partnersFragmentTag).commit();
				}
			}
			if (!partnersFragment.isAdded()) {
				fragmentManager.beginTransaction().replace(R.id.container, partnersFragment, partnersFragmentTag).commit();
			}
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
