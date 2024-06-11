package br.com.ushorter.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.model.UrlMappingClick;
import br.com.ushorter.repository.UrlMappingClickRepository;
import br.com.ushorter.service.validation.ValidationClickQuantityService;
import br.com.ushorter.util.UtilsUrl;

@Service
public class UrlMappingClickService {
	
	private UrlMappingClickRepository urlMappingClickRepository;
	
	private ValidationClickQuantityService validationClickQuantityService;

	public UrlMappingClickService(UrlMappingClickRepository urlMappingClickRepository,
			ValidationClickQuantityService validationClickQuantityService) {
		this.urlMappingClickRepository = urlMappingClickRepository;
		this.validationClickQuantityService = validationClickQuantityService;
	}

	public void registerClick(UrlMapping urlMapping) {
		UrlMappingClick urlMappingClick = new UrlMappingClick();
		urlMappingClick.setUrlMapping(urlMapping);
		urlMappingClick.setUrlClickTime(LocalDateTime.now());
		
		urlMappingClickRepository.save(urlMappingClick);
	}

	public int findQuantityClick(String shortUrl, String language) throws Exception {
		String hashFromUrl = UtilsUrl.extractHashFromUrl(shortUrl);
		validationClickQuantityService.executeValidations(hashFromUrl, language);
		
		return urlMappingClickRepository.findByUrlMapping_ShortUrl(hashFromUrl).size();
	} 

}
