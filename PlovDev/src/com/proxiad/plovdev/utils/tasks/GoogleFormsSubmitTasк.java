package com.proxiad.plovdev.utils.tasks;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.proxiad.plovdev.utils.HttpManager;

import android.os.AsyncTask;
import android.util.Log;

public class GoogleFormsSubmitTas� extends AsyncTask<String, Void, Boolean> {

	private static final String LOG_TAG = "GoogleFormsSubmitTas�";

	// TODO display progress bar
	@Override
	protected Boolean doInBackground(String... params) {

		try {
			HttpGet submitToGoogleForms = new HttpGet(params[0]);
			HttpResponse response = HttpManager.execute(submitToGoogleForms);
			if (response != null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, "Error connecting", e);
		}
		return true;
	}

}
