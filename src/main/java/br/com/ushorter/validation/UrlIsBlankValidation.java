package br.com.ushorter.validation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.ushorter.exception.RedirectUrlShortIsBlankException;
import br.com.ushorter.validation.interfaces.RedirectValidationInterface;
import io.micrometer.common.util.StringUtils;

@Component
public class UrlIsBlankValidation implements RedirectValidationInterface {

	private MessageSource messageSource;

	public UrlIsBlankValidation(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void execute(String url, Locale locale) throws Exception {
		if (isShortUrlIsBlank(url)) {
			String message = messageSource.getMessage(RedirectUrlShortIsBlankException.MESSAGE, new Object[] {}, locale);
			throw new RedirectUrlShortIsBlankException(message);
		}
	}

	public boolean isShortUrlIsBlank(String url) {
		return StringUtils.isBlank(url);
	}

}
