package br.com.ushorter.validation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class UrlAcessibleValidation {
	
	private MessageSource messageSource;

	public UrlAcessibleValidation(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
//
//	@Override
//	public void execute(String url, Locale locale) throws Exception {
//		if (!isUrlOriginalAccessible(url)) {
//			String message = messageSource.getMessage(OriginalUrlWithInvalidAccessException.MESSAGE,
//					new Object[] { url }, locale);
//			throw new OriginalUrlWithInvalidAccessException(message);
//		}
//	}
//
//	public boolean isUrlOriginalAccessible(String url) {
//		 RestTemplate restTemplate = new RestTemplate();
//	        try {
//	            restTemplate.headForHeaders(url);
//	            return true;
//	        } catch (HttpClientErrorException e) {
//	            return false;
//	        }catch (IllegalArgumentException e) {
//	            return false;
//	        }
//	}

}
