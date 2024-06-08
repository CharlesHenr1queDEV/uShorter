package br.com.ushorter.validation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.micrometer.common.util.StringUtils;

public class UrlValidationImpl implements UrlValidation {

	@Override
	public boolean isValidUrl(String url) {
		try {
			new URL(url);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	@Override
	public boolean isUrlOriginalAccessible(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
	        connection.setConnectTimeout(3000); 
			connection.setReadTimeout(5000);
			int responseCode = connection.getResponseCode();
			return (200 <= responseCode && responseCode <= 299);
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean isOriginalUrlBlank(String originalUrl) {
		return StringUtils.isBlank(originalUrl);
	}
}
