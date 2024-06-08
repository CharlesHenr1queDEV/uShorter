package br.com.ushorter.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.dto.UrlMappingDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.repository.UrlMappingRepository;
import br.com.ushorter.validation.ValidationInterface;

@Service
public class UrlMappingService {
	
	private final Logger logger = LogManager.getLogger(UrlMappingService.class);

	private UrlMappingRepository urlMappingRepository;
	
	private UrlMappingClickService urlMappingClickService;
	
	private MessageSource messageSource;

	public UrlMappingService(UrlMappingRepository urlMappingRepository, UrlMappingClickService urlMappingClickService, MessageSource messageSource) {
		this.urlMappingRepository = urlMappingRepository;
		this.urlMappingClickService = urlMappingClickService;
		this.messageSource = messageSource;
	}
	
	public UrlMapping saveUrlMapping(OriginalUrlDTO originalUrlRecord, String language) throws Exception {
		logger.info("[URL SERVICE] Iniciando validação para geração de um url curto");
		ValidationInterface validate = new ValidationUrlMappingService(originalUrlRecord, language, messageSource);
		validate.execute();
		
		logger.info("[URL SERVICE] Gerando url curto para url: " + originalUrlRecord.url());
		String shortUrl = generateShortUrl(originalUrlRecord.url());
		
		UrlMappingDTO urlMappingDTO = new UrlMappingDTO(originalUrlRecord.url(), shortUrl);
		
		logger.info("[URL SERVICE] Processamento finalizado, gerando url curta");
		return urlMappingRepository.save(urlMappingDTO.parseUrlMapping());
	}
	
	public String redirectUrl(String shortUrl, String language) throws Exception {
		logger.info("[URL SERVICE]  Iniciando validação para short url");
		ValidationInterface validate = new ValidationRedirectUrlService(shortUrl, language, messageSource, this);
		validate.execute();
		
		UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
		
		logger.info("[URL SERVICE]  Registrando click na short Url: " + shortUrl);
		urlMappingClickService.registerClick(urlMapping);
		
		logger.info("[URL SERVICE] Redirecionando para a url: " +  urlMapping.getOriginalUrl());
		return urlMapping.getOriginalUrl();
	}
	
	public UrlMapping findByShortUrl(String shortUrl) {
		return urlMappingRepository.findByShortUrl(shortUrl);
	}
	
	public String generateShortUrl(String originalUrl) {
		//Criar método de validação no mesmo formato dos demais para verificar se existe algum hash igual ao gerado, caso já exista gerar outro
		//Criar um modelo de hash com 5 ou 6 caracters randomico
		return Integer.toHexString(originalUrl.hashCode());
	}
	
}
