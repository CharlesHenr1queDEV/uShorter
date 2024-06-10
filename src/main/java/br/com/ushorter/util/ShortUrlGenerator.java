package br.com.ushorter.util;

import org.springframework.stereotype.Component;

@Component
public class ShortUrlGenerator {

	public String generateHash(String originalUrl) {
		URLService url = new URLService();
		String longToShort = url.generateRandomShortUrl();
		return longToShort;
	}
}
