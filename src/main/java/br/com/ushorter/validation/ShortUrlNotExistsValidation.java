package br.com.ushorter.validation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.ushorter.exception.ShortUrlNotExistsException;
import br.com.ushorter.repository.UrlMappingClickRepository;
import br.com.ushorter.validation.interfaces.ClickQuantityValidationInterface;

@Component
public class ShortUrlNotExistsValidation implements ClickQuantityValidationInterface {

	private UrlMappingClickRepository urlMappingClickRepository;

	private MessageSource messageSource;

	public ShortUrlNotExistsValidation(UrlMappingClickRepository urlMappingClickRepository, MessageSource messageSource) {
		this.urlMappingClickRepository = urlMappingClickRepository;
		this.messageSource = messageSource;
	}

	@Override
	public void execute(String shortUrl, Locale locale) throws Exception {
		if (!isShortUrlPresent(shortUrl)) {
			String message = messageSource.getMessage(ShortUrlNotExistsException.MESSAGE, new Object[] { shortUrl },
					locale);
			throw new ShortUrlNotExistsException(message);
		}

	}

	public boolean isShortUrlPresent(String url) {
		return urlMappingClickRepository.existsByUrlMapping_ShortUrl(url);
	}

}
