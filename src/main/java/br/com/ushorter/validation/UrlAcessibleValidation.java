package br.com.ushorter.validation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.ushorter.exception.OriginalUrlWithInvalidAccessException;
import br.com.ushorter.validation.interfaces.UrlMappingValidationInterface;

@Component
public class UrlAcessibleValidation implements UrlMappingValidationInterface {
	
	private MessageSource messageSource;

	public UrlAcessibleValidation(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void execute(String url, Locale locale) throws Exception {
		if (!isUrlOriginalAccessible(url)) {
			String message = messageSource.getMessage(OriginalUrlWithInvalidAccessException.MESSAGE,
					new Object[] { url }, locale);
			throw new OriginalUrlWithInvalidAccessException(message);
		}
	}

	public boolean isUrlOriginalAccessible(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(5000);
			int responseCode = connection.getResponseCode();
			return (200 <= responseCode && responseCode <= 299);
		} catch (IOException e) {
			return false;
		}
	}

}
