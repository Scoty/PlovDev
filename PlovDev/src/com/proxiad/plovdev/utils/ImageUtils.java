package com.proxiad.plovdev.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.proxiad.plovdev.R;

@SuppressLint("SimpleDateFormat")
public class ImageUtils {
	private static final HashMap<String, SoftReference<FastBitmapDrawable>> drawablesCache = new HashMap<String, SoftReference<FastBitmapDrawable>>();

	private static SimpleDateFormat sLastModifiedFormat;

	private ImageUtils() {
	}

	/**
	 * A Bitmap associated with its last modification date.
	 */
	public static class ExpiringBitmap {
		public Bitmap bitmap;
		public Calendar lastModified;
	}

	/**
	 * Deletes the specified drawable from the cache. Calling this method will
	 * remove the drawable from the in-memory cache and delete the corresponding
	 * file from the external storage.
	 * 
	 * @param id
	 *            The id of the drawable to delete from the cache
	 */
	public static void deleteCachedDrawable(String id) {
		new File(ImportUtils.getCacheDirectory(), id).delete();
		drawablesCache.remove(id);
	}

	public static FastBitmapDrawable getCachedDrawable(String id) {
		FastBitmapDrawable drawable = null;

		SoftReference<FastBitmapDrawable> reference = drawablesCache.get(id);
		if (reference != null) {
			drawable = reference.get();
		}

		if (drawable == null) {
			final Bitmap bitmap = loadBitmap(id);
			if (bitmap != null) {
				drawable = new FastBitmapDrawable(bitmap);
			}
			drawablesCache.put(id, new SoftReference<FastBitmapDrawable>(drawable));
		}

		return drawable;
	}

	public static FastBitmapDrawable getDefaultDrawable(Context context) {
		FastBitmapDrawable drawable = null;
		String defaultId = "default";

		SoftReference<FastBitmapDrawable> reference = drawablesCache.get(defaultId);
		if (reference != null) {
			drawable = reference.get();
		} else {
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.speaker_anton_antonov);
			drawable = new FastBitmapDrawable(bitmap);
			drawablesCache.put(defaultId, new SoftReference<FastBitmapDrawable>(drawable));
		}

		return drawable;
	}

	/**
	 * Removes all the callbacks from the drawables stored in the memory cache.
	 * This method must be called from the onDestroy() method of any activity
	 * using the cached drawables. Failure to do so will result in the entire
	 * activity being leaked.
	 */
	public static void cleanupCache() {
		for (SoftReference<FastBitmapDrawable> reference : drawablesCache.values()) {
			final FastBitmapDrawable drawable = reference.get();
			if (drawable != null)
				drawable.setCallback(null);
		}
	}

	/**
	 * Loads an image from the specified URL.
	 * 
	 * @param url
	 *            The URL of the image to load.
	 * 
	 * @return The image at the specified URL or null if an error occured.
	 */
	public static ExpiringBitmap load(String url) {
		return load(url, null);
	}

	/**
	 * Loads an image from the specified URL with the specified cookie.
	 * 
	 * @param url
	 *            The URL of the image to load.
	 * @param cookie
	 *            The cookie to use to load the image.
	 * 
	 * @return The image at the specified URL or null if an error occurred.
	 */
	public static ExpiringBitmap load(String url, String cookie) {

		ExpiringBitmap expiring = null;
		try {
			expiring = new ImageLoadingTask().execute(url).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return expiring;
	}

	public static void setLastModified(ExpiringBitmap expiring, HttpResponse response) {
		expiring.lastModified = null;

		final Header header = response.getFirstHeader("Last-Modified");
		if (header == null)
			return;

		if (sLastModifiedFormat == null) {
			sLastModifiedFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		}

		final Calendar calendar = GregorianCalendar.getInstance();
		try {
			calendar.setTime(sLastModifiedFormat.parse(header.getValue()));
			expiring.lastModified = calendar;
		} catch (ParseException e) {
			// Ignore
		}
	}

	private static Bitmap loadBitmap(String id) {
		final File file = new File(ImportUtils.getCacheDirectory(), id);
		if (file.exists()) {
			InputStream stream = null;
			try {
				stream = new FileInputStream(file);
				return BitmapFactory.decodeStream(stream, null, null);
			} catch (FileNotFoundException e) {
				// Ignore
			} finally {
				IOUtils.closeStream(stream);
			}
		}
		return null;
	}
}