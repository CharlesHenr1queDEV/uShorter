package br.com.ushorter.validation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.ushorter.exception.RedirectUrlShortUrlNotExistsException;
import br.com.ushorter.repository.UrlMappingRepository;
import br.com.ushorter.validation.interfaces.RedirectValidationInterface;

@Component
public class ExistUrlMappingByShortUrlValidation implements RedirectValidationInterface {

	private UrlMappingRepository urlMappingRepository;
	private MessageSource messageSource;
	
	public ExistUrlMappingByShortUrlValidation(UrlMappingRepository urlMappingRepository, MessageSource messageSource) {
		this.urlMappingRepository = urlMappingRepository;
		this.messageSource = messageSource;
	}

	@Override
	public void execute(String url, Locale locale) throws Exception {
		if(!isExistUrlMappingByShortUrl(url)) {
			String message = messageSource.getMessage(RedirectUrlShortUrlNotExistsException.MESSAGE, new Object[] {}, locale);
			throw new RedirectUrlShortUrlNotExistsException(message);
		}
	}
	
	public boolean isExistUrlMappingByShortUrl(String shortUrl) {
		return urlMappingRepository.findByShortUrl(shortUrl) != null ? true : false ;
	}

}
