package br.com.ushorter.validation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.ushorter.exception.OriginalUrlIsBlankException;
import br.com.ushorter.validation.interfaces.UrlMappingValidationInterface;
import io.micrometer.common.util.StringUtils;

@Component
public class OrinalUrlIsBlankValidation implements UrlMappingValidationInterface {

	private MessageSource messageSource;
	
	public OrinalUrlIsBlankValidation(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void execute(String url, Locale locale) throws Exception {
		if (isOriginalUrlBlank(url)) {
			String message = messageSource.getMessage(OriginalUrlIsBlankException.MESSAGE, new Object[] {}, locale);
			throw new OriginalUrlIsBlankException(message);
		}
	}

	public boolean isOriginalUrlBlank(String url) {
		return StringUtils.isBlank(url);
	}

}
