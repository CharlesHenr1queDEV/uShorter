package br.com.ushorter.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ShortUrlGenerator {

	private final Random random = new Random();

	public String generateHash(String originalUrl) {
		URLService url = new URLService();
		String longToShort = url.generateRandomShortUrl();
		return longToShort;
	}
}
