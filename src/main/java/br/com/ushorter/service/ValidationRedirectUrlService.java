package br.com.ushorter.service;

import java.util.Locale;

import org.springframework.context.MessageSource;

import br.com.ushorter.exception.RedirectUrlShortUrlNotExists;
import br.com.ushorter.util.UtilsUrl;
import br.com.ushorter.validation.RedirectValidation;
import br.com.ushorter.validation.RedirectValidationImpl;
import br.com.ushorter.validation.ValidationInterface;


public class ValidationRedirectUrlService implements ValidationInterface {

	private String shortUrl;
	private RedirectValidation redirectValidation;
	private Locale locale;
	private MessageSource messageSource;

	public ValidationRedirectUrlService(String shortUrl, String locale, MessageSource messageSource, UrlMappingService urlMappingService) {
		this.shortUrl = shortUrl;
		this.redirectValidation = new RedirectValidationImpl(urlMappingService);
		this.locale = UtilsUrl.getLocaleByLanguage(locale);
		this.messageSource = messageSource;
	}

	@Override
	public void execute() throws Exception {
		if (!redirectValidation.isExistUrlMappingByShortUrl(shortUrl)) {
			String message = messageSource.getMessage(RedirectUrlShortUrlNotExists.MESSAGE, new Object[] {}, locale);
			throw new RedirectUrlShortUrlNotExists(message);
		}
	}
}
