package br.com.ushorter.validation.interfaces;

import java.util.Locale;

public interface UrlMappingValidationInterface {
	
	public void execute(String url, Locale locale) throws Exception;

}
