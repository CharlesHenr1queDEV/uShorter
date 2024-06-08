package br.com.ushorter.validation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.ushorter.exception.OriginalUrlWithInvalidFormatException;
import br.com.ushorter.validation.interfaces.UrlMappingValidationInterface;

@Component
public class UrlFormatValidaton implements UrlMappingValidationInterface {
	
	private MessageSource messageSource;
	
	public UrlFormatValidaton(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void execute(String url, Locale locale) throws Exception {
		if (!isValidUrl(url)) {
			String message = messageSource.getMessage(OriginalUrlWithInvalidFormatException.MESSAGE, new Object[] {url}, locale);
			throw new OriginalUrlWithInvalidFormatException(message);
		}
	}

	public boolean isValidUrl(String url) {
		try {
			new URL(url);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

}
