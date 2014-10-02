package com.proxiad.plovdev.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.os.AsyncTask;

public class JsonUtils extends AsyncTask<String, Void, String> {
	// TODO display progress bar
	@Override
	protected String doInBackground(String... params) {

		InputStream inputStream = null;
		String result = null;

		try {
			HttpGet getJson = new HttpGet(params[0]);
			HttpResponse response = HttpManager.execute(getJson);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
			// json is UTF-8 by default
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Exception squish) {
			}
		}
		return result;
	}

}