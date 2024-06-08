package br.com.ushorter.service;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.model.UrlMappingClick;
import br.com.ushorter.repository.UrlMappingClickRepository;
import br.com.ushorter.service.validation.ValidationClickQuantityService;

@Service
public class UrlMappingClickService {
	
	private final Logger logger = LogManager.getLogger(UrlMappingClickService.class);

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
		validationClickQuantityService.executeValidations(shortUrl, language);
		
		return urlMappingClickRepository.findByUrlMapping_ShortUrl(shortUrl).size();
	} 

}
