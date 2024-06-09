package br.com.ushorter.service.validation;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import br.com.ushorter.util.UtilsUrl;
import br.com.ushorter.validation.interfaces.RedirectValidationInterface;

@Service
public class ValidationRedirectUrlService {

	private List<RedirectValidationInterface> validations;

	public ValidationRedirectUrlService(List<RedirectValidationInterface> validations) {
		this.validations = validations;
	}

	public void executeValidations(String shortUrl, String language) throws Exception {
		Locale locale = UtilsUrl.getLocaleByLanguage(language);

		for (RedirectValidationInterface redirectValidationInterface : validations) {
			redirectValidationInterface.execute(shortUrl, locale);
		}
	}

}
