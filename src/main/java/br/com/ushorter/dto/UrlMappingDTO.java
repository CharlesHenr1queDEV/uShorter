package br.com.ushorter.dto;

import br.com.ushorter.model.UrlMapping;

public record UrlMappingDTO(String originalUrl, String shortUrl) {
	
    public UrlMapping parseUrlMapping() {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);

        return urlMapping;
    }
}
