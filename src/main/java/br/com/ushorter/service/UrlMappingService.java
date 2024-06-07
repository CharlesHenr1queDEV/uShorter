package br.com.ushorter.service;

import org.springframework.stereotype.Service;

import br.com.ushorter.dto.UrlMappingDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.repository.UrlMappingRepository;

@Service
public class UrlMappingService {

	private UrlMappingRepository urlMappingRepository;
	
	private UrlMappingClickService urlMappingClickService;

	public UrlMappingService(UrlMappingRepository urlMappingRepository, UrlMappingClickService urlMappingClickService) {
		this.urlMappingRepository = urlMappingRepository;
		this.urlMappingClickService = urlMappingClickService;
	}
	
	public UrlMapping saveUrlMapping(UrlMappingDTO urlMappingDTO) {
		String shortUrl = generateShortUrl(urlMappingDTO.getOriginalUrl());
		urlMappingDTO.setShortUrl(shortUrl);
		
		return urlMappingRepository.save(urlMappingDTO.parseUrlMapping());
	}
	
	public UrlMappingDTO redirectUrl(String shortUrl) {
		UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
		
		if(urlMapping != null) {
			urlMappingClickService.registerClick(urlMapping);
			return urlMappingRepository.findByShortUrl(shortUrl).parseUrlMappingDTO();
		}else {
			return null;
		}
	}
	
	public String generateShortUrl(String originalUrl) {
		//Validações a serem feitas antes de gerar
		return Integer.toHexString(originalUrl.hashCode());
	}
	
}
