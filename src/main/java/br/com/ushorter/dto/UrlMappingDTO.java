package br.com.ushorter.dto;

import br.com.ushorter.model.UrlMapping;

public class UrlMappingDTO {

	private String originalUrl;

	private String shortUrl;

	private int urlClickCount;

	public UrlMappingDTO(String originalUrl, String shortUrl, int urlClickCount) {
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.urlClickCount = urlClickCount;
	}

	public UrlMappingDTO() {
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public int getUrlClickCount() {
		return urlClickCount;
	}

	public void setUrlClickCount(int urlClickCount) {
		this.urlClickCount = urlClickCount;
	}

	public UrlMapping parseUrlMapping() {
		UrlMapping urlMapping = new UrlMapping();
		urlMapping.setOriginalUrl(originalUrl);
		urlMapping.setShortUrl(shortUrl);

		return urlMapping;
	}

}
