package ggboy.study.java.common.utils.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import ggboy.study.java.common.exception.DemoException;
import ggboy.study.java.common.utils.string.StringUtil;

public class SimpleHttpClient {

	private static final int maxTotal = 10;
	private static final int maxPerRoute = 10;
	private static final int connectionRequestTimeout = 6000;
	private static final int connectTimeout = 6000;
	private static final int socketTimeout = 6000;
	private static final String default_charset = "utf-8";
	private static HttpClient client;

	public static HttpResponse get(HttpClient httpClient, String url, Map<String, String> param) {
		StringBuilder sb = new StringBuilder(url + "?");
		for (Entry<String, String> entry : param.entrySet()) {
			if (StringUtil.isNotEmpty(entry.getValue()))
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if (sb.length() > url.length() + 1) {
			sb = sb.delete(sb.length() - 1, sb.length());
		}
		return get(httpClient, sb.toString());
	}

	public static HttpResponse get(HttpClient httpClient, String url) {
		try {
			HttpGet req = new HttpGet(url);
			return httpClient.execute(req);
		} catch (Exception e) {
			throw new DemoException("发送http请求时异常", e);
		}
	}

	public static HttpResponse post(HttpClient httpClient, String url, Map<String, String> param) {
		return post(httpClient, url, param, null);
	}

	public static HttpResponse post(HttpClient httpClient, String url, byte[] data) {
		HttpEntity entity = new ByteArrayEntity(data);
		return post(httpClient, url, entity);
	}

	public static HttpResponse post(HttpClient httpClient, String url, Map<String, String> param, String charset) {
		try {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : param.entrySet()) {
				if (entry.getValue() != null && !entry.getValue().equals("")) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			return post(httpClient, url,
					new UrlEncodedFormEntity(formParams, charset == null ? default_charset : charset));
		} catch (Exception e) {
			throw new DemoException("发送http请求时异常", e);
		}
	}

	private static HttpResponse post(HttpClient httpClient, String url, HttpEntity entity) {
		try {
			HttpPost req = new HttpPost(url);
			req.setEntity(entity);
			return httpClient.execute(req);
		} catch (Exception e) {
			throw new DemoException("发送http请求时异常", e);
		}
	}

	public static HttpClient getClient() {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", plainsf).register("https", sslsf).build();

		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
		connManager.setMaxTotal(maxTotal);
		connManager.setDefaultMaxPerRoute(maxPerRoute);

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
				.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();

		return HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
	}

	public static HttpClient client() {
		if (client != null) {
			return client;
		}
		synchronized (SimpleHttpClient.class) {
			if (client != null) {
				return client;
			}
			client = SimpleHttpClient.getClient();
			return client;
		}
	}
}
