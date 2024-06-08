package br.com.ushorter.validation;

import br.com.ushorter.service.UrlMappingService;


public class RedirectValidationImpl implements RedirectValidation{

	private UrlMappingService urlMappingService;
	
	public RedirectValidationImpl(UrlMappingService urlMappingService) {
		this.urlMappingService = urlMappingService;
	}
	
	@Override
	public boolean isExistUrlMappingByShortUrl(String shortUrl) {
		return urlMappingService.findByShortUrl(shortUrl) != null ? true : false ;
	}

	@Override
	public boolean isShortUrlIsBlank(String shortUrl) {
		// TODO Auto-generated method stub
		return false;
	}

}
