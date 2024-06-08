package br.com.ushorter.util;

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
	
}
