package br.com.ushorter.service;

import java.util.Locale;

import org.springframework.context.MessageSource;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.exception.OriginalUrlIsBlankException;
import br.com.ushorter.exception.OriginalUrlWithInvalidAccessException;
import br.com.ushorter.exception.OriginalUrlWithInvalidFormatException;
import br.com.ushorter.util.UtilsUrl;
import br.com.ushorter.validation.UrlValidation;
import br.com.ushorter.validation.UrlValidationImpl;
import br.com.ushorter.validation.ValidationInterface;

public class ValidationUrlMappingService implements ValidationInterface {

	private OriginalUrlDTO originalUrlRecord;
	private UrlValidation urlValidation;
	private Locale locale;
	private MessageSource messageSource;

	public ValidationUrlMappingService(OriginalUrlDTO originalUrlRecord, String locale, MessageSource messageSource) {
		this.originalUrlRecord = originalUrlRecord;
		this.urlValidation = new UrlValidationImpl();
		this.locale = UtilsUrl.getLocaleByLanguage(locale);
		this.messageSource = messageSource;
	}

	@Override
	public void execute() throws Exception {
		if (urlValidation.isOriginalUrlBlank(originalUrlRecord.url())) {
			String message = messageSource.getMessage(OriginalUrlIsBlankException.MESSAGE, new Object[] {}, locale);
			throw new OriginalUrlIsBlankException(message);
		}

		if (!urlValidation.isValidUrl(originalUrlRecord.url())) {
			String message = messageSource.getMessage(OriginalUrlWithInvalidFormatException.MESSAGE, new Object[] {originalUrlRecord.url() }, locale);
			throw new OriginalUrlWithInvalidFormatException(message);
		}

		if (!urlValidation.isUrlOriginalAccessible(originalUrlRecord.url())) {
			String message = messageSource.getMessage(OriginalUrlWithInvalidAccessException.MESSAGE, new Object[] { originalUrlRecord.url() }, locale);
			throw new OriginalUrlWithInvalidAccessException(message);
		}
	}
}
