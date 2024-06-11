package br.com.ushorter.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import io.micrometer.common.util.StringUtils;

public class UtilsUrl {
	
	private static final String LANGUAGE_PT_BR = "br";
	private static final String LANGUAGE_EN = "en";

	public static Locale getLocaleByLanguage(String language) {
		Locale locale = null;
		if(!StringUtils.isBlank(language) && (language.equalsIgnoreCase(LANGUAGE_PT_BR) || language.equalsIgnoreCase(LANGUAGE_EN))) {
			locale = new Locale(language);				
		}else {
			locale = new Locale(LANGUAGE_PT_BR);
		}
		return locale;
	}
	
	public static String extractHashFromUrl(String fullUrl) {
		try {
			URI uri = new URI(fullUrl);
			String path = uri.getPath();

			if (path.startsWith("/")) {
				path = path.substring(1);
			}
			return path;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
