package br.com.ushorter.service.validation;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import br.com.ushorter.util.UtilsUrl;
import br.com.ushorter.validation.ShortUrlNotExistsValidation;

@Service
public class ValidationClickQuantityService {

	private List<ShortUrlNotExistsValidation> validations;

	public ValidationClickQuantityService(List<ShortUrlNotExistsValidation> validations) {
		this.validations = validations;
	}

	public void executeValidations(String shortUrl, String language) throws Exception {
		Locale locale = UtilsUrl.getLocaleByLanguage(language);

		for (ShortUrlNotExistsValidation shortUrlNotExistsValidation : validations) {
			shortUrlNotExistsValidation.execute(shortUrl, locale);
		}
	}
}
