package br.com.ushorter.service.validation;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import br.com.ushorter.util.UtilsUrl;
import br.com.ushorter.validation.interfaces.UrlMappingValidationInterface;

@Service
public class ValidationUrlMappingService {

	private List<UrlMappingValidationInterface> validations;

	public ValidationUrlMappingService(List<UrlMappingValidationInterface> validations) {
		this.validations = validations;
	}

	public void executeValidations(String originalUrl, String language) throws Exception {
		Locale locale = UtilsUrl.getLocaleByLanguage(language);

		for (UrlMappingValidationInterface urlMappingValidationInterface : validations) {
			urlMappingValidationInterface.execute(originalUrl, locale);
		}
	}

}
