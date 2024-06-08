package br.com.ushorter.validation;

public interface UrlValidation {
	boolean isValidUrl(String url);
    boolean isUrlOriginalAccessible(String originalUrl);
    boolean isOriginalUrlBlank(String originalUrl);
}
