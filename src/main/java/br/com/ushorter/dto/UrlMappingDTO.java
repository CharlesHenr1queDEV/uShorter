package br.com.ushorter.dto;

import br.com.ushorter.model.UrlMapping;

public record UrlMappingDTO(String originalUrl, String shortUrl) {
	
    public UrlMapping parseUrlMapping() {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);

        return urlMapping;
    }
    
    public UrlMappingDTO withOriginalUrl(String originalUrl) {
        return new UrlMappingDTO(originalUrl, this.shortUrl);
    }
    
    public UrlMappingDTO withShortUrl(String shortUrl) {
        return new UrlMappingDTO(this.originalUrl, shortUrl);
    }
    
    
    public UrlMappingDTO withConcatPrefixUrl(String prefix) {
        return new UrlMappingDTO(this.originalUrl, prefix.concat(this.shortUrl));
    }
}
