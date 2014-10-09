package com.proxiad.plovdev.utils;

import org.apache.http.params.HttpParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ClientConnectionManager;

import java.io.IOException;

public class HttpManager {
	private static final DefaultHttpClient sClient;
	static {
		final HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");

		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
		HttpConnectionParams.setSoTimeout(params, 20 * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192);

		HttpClientParams.setRedirecting(params, false);

		HttpProtocolParams.setUserAgent(params, "PlovDev/1.0");

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

		ClientConnectionManager manager = new ThreadSafeClientConnManager(params, schemeRegistry);
		sClient = new DefaultHttpClient(manager, params);
	}

	private HttpManager() {
	}

	public static HttpResponse execute(HttpHead head) throws IOException {
		return sClient.execute(head);
	}

	public static HttpResponse execute(HttpHost host, HttpGet get) throws IOException {
		return sClient.execute(host, get);
	}

	public static HttpResponse execute(HttpGet get) throws IOException {
		return sClient.execute(get);
	}

	// Post requests are not needed atm

	// public static HttpResponse execute(HttpPost post) throws IOException {
	// return sClient.execute(post);
	// }

}
