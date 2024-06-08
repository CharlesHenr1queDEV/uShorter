package br.com.ushorter.validation;

public interface RedirectValidation {
	boolean isExistUrlMappingByShortUrl(String shortUrl);
	boolean isShortUrlIsBlank(String shortUrl);
}
