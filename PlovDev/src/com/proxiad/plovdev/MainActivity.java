package com.proxiad.plovdev;

import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private final String mainFragmentTag = "mainFragment";
	private final String speakersFragmentTag = "speakersFragment";
	private final String venueFragmentTag = "venueFragment";
	private final String partnersFragmentTag = "partnersFragment";

	private Fragment mainFragment;
	private Fragment speakersFragment;
	private Fragment venueFragment;
	private Fragment partnersFragment;

	private int position;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	public NavigationDrawerFragment getNavigationDrawerFragment() {
		return mNavigationDrawerFragment;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setLocale(this);
		setContentView(R.layout.activity_main);
		ImageView logoImageView = (ImageView) findViewById(R.id.imageLogoPlovdev);
		logoImageView.setImageResource(R.drawable.logo_plovdev);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onResume() {
		super.onResume();
		getActionBar().setTitle(R.string.first_day);
	}

	public void onRetoreInstanceState(Bundle savedInstanceState) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		mainFragment = fragmentManager.getFragment(savedInstanceState, mainFragmentTag);
		speakersFragment = fragmentManager.getFragment(savedInstanceState, speakersFragmentTag);
		venueFragment = fragmentManager.getFragment(savedInstanceState, venueFragmentTag);
		partnersFragment = fragmentManager.getFragment(savedInstanceState, partnersFragmentTag);
		position = savedInstanceState.getInt("position");
		onNavigationDrawerItemSelected(position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("position", position);

		FragmentManager fragmentManager = getSupportFragmentManager();
		if (mainFragment != null) {
			fragmentManager.putFragment(outState, mainFragmentTag, mainFragment);
		}
		if (speakersFragment != null) {
			fragmentManager.putFragment(outState, speakersFragmentTag, speakersFragment);
		}
		if (venueFragment != null) {
			fragmentManager.putFragment(outState, venueFragmentTag, venueFragment);
		}
		if (partnersFragment != null) {
			fragmentManager.putFragment(outState, partnersFragmentTag, partnersFragment);
		}
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		hideAllFragments(fragmentManager);
		this.position = position;
		switch (position) {
		case 0:
			mTitle = getString(R.string.first_day);
			mainFragment = fragmentManager.findFragmentByTag(mainFragmentTag);
			if (mainFragment == null) {
				mainFragment = new MainFragment();
				fragmentManager.beginTransaction().add(R.id.container, mainFragment, mainFragmentTag).commit();
			}
			fragmentManager.beginTransaction().show(mainFragment).commit();
			break;
		case 1:
			mTitle = getString(R.string.speakers);
			speakersFragment = fragmentManager.findFragmentByTag(speakersFragmentTag);
			if (speakersFragment == null) {
				speakersFragment = new SpeakersFragment();
				fragmentManager.beginTransaction().add(R.id.container, speakersFragment, speakersFragmentTag).commit();
			}
			fragmentManager.beginTransaction().show(speakersFragment).commit();
			break;
		case 2:
			mTitle = getString(R.string.venue);
			venueFragment = fragmentManager.findFragmentByTag(venueFragmentTag);
			if (venueFragment == null) {
				venueFragment = new VenueFragment();
				fragmentManager.beginTransaction().add(R.id.container, venueFragment, venueFragmentTag).commit();
			}
			fragmentManager.beginTransaction().show(venueFragment).commit();
			break;
		case 3:
			mTitle = getString(R.string.partners);
			partnersFragment = fragmentManager.findFragmentByTag(partnersFragmentTag);
			if (partnersFragment == null) {
				partnersFragment = new PartnersFragment();
				fragmentManager.beginTransaction().add(R.id.container, partnersFragment, partnersFragmentTag).commit();
			}
			fragmentManager.beginTransaction().show(partnersFragment).commit();
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
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			mNavigationDrawerFragment.closeDrawer();
			return;
		}
		if (mainFragment == null) {
			this.position = 0;
			mainFragment = fragmentManager.findFragmentByTag(mainFragmentTag);
			if (mainFragment == null) {
				mainFragment = new MainFragment();
				fragmentManager.beginTransaction().add(R.id.container, mainFragment, mainFragmentTag).commit();
			}
			hideAllFragments(fragmentManager);
			fragmentManager.beginTransaction().show(mainFragment).commit();
			mNavigationDrawerFragment.setCurrentPosition(0);
			getActionBar().setTitle(R.string.first_day);
		} else if (mainFragment.isHidden()) {
			this.position = 0;
			hideAllFragments(fragmentManager);
			fragmentManager.beginTransaction().show(mainFragment).commit();
			mNavigationDrawerFragment.setCurrentPosition(0);
			getActionBar().setTitle(R.string.first_day);
		} else {
			super.onBackPressed();
		}
	}

	public void onLogoClicked(View view) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (mainFragment == null) {
			this.position = 0;
			mainFragment = fragmentManager.findFragmentByTag(mainFragmentTag);
			if (mainFragment == null) {
				mainFragment = new MainFragment();
				fragmentManager.beginTransaction().add(R.id.container, mainFragment, mainFragmentTag).commit();
			}
			hideAllFragments(fragmentManager);
			fragmentManager.beginTransaction().show(mainFragment).commit();
			mNavigationDrawerFragment.setCurrentPosition(0);
			getActionBar().setTitle(R.string.first_day);
		} else {
			this.position = 0;
			hideAllFragments(fragmentManager);
			fragmentManager.beginTransaction().show(mainFragment).commit();
			mNavigationDrawerFragment.setCurrentPosition(0);
			getActionBar().setTitle(R.string.first_day);
		}
	}

	private void hideAllFragments(FragmentManager fragmentManager) {
		List<Fragment> fragments = fragmentManager.getFragments();
		for (Fragment fragment : fragments) {
			if (fragment.getId() != R.id.navigation_drawer) {
				fragmentManager.beginTransaction().hide(fragment).commit();
			}
		}
	}

	private void setLocale(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String lang = sharedPreferences.getString(SettingsActivity.KEY_PREF_LIST_LANGUAGE, "en");
		Locale locale = new Locale(lang);
		Resources res = context.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = locale;
		res.updateConfiguration(conf, dm);
	}
}
