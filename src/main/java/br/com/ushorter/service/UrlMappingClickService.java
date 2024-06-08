package br.com.ushorter.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.model.UrlMappingClick;
import br.com.ushorter.repository.UrlMappingClickRepository;

@Service
public class UrlMappingClickService {

	private UrlMappingClickRepository urlMappingClickRepository;

	public UrlMappingClickService(UrlMappingClickRepository urlMappingClickRepository) {
		this.urlMappingClickRepository = urlMappingClickRepository;
	}

	public void registerClick(UrlMapping urlMapping) {
		// criar processo de validação e tratamento de erro
		UrlMappingClick urlMappingClick = new UrlMappingClick();
		urlMappingClick.setUrlMapping(urlMapping);
		urlMappingClick.setUrlClickTime(LocalDateTime.now());
		
		urlMappingClickRepository.save(urlMappingClick);
	}

	public int findQuantityClick(String shortUrl) {
		// criar processo de validação e tratamento de erro
		return urlMappingClickRepository.findByUrlMapping_ShortUrl(shortUrl).size();
	}

}
