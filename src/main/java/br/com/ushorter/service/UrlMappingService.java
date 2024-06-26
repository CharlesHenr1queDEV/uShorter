package br.com.ushorter.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.ushorter.dto.OriginalUrlDTO;
import br.com.ushorter.dto.UrlMappingDTO;
import br.com.ushorter.model.UrlMapping;
import br.com.ushorter.repository.UrlMappingRepository;
import br.com.ushorter.service.validation.ValidationRedirectUrlService;
import br.com.ushorter.service.validation.ValidationUrlMappingService;
import br.com.ushorter.util.ShortUrlGenerator;

@Service
public class UrlMappingService {
	
	private final Logger logger = LogManager.getLogger(UrlMappingService.class);

	private UrlMappingRepository urlMappingRepository;
	
	private UrlMappingClickService urlMappingClickService;
	private ShortUrlGenerator shortUrlGenerator;
	private ValidationRedirectUrlService validationRedirectUrlService;
	private ValidationUrlMappingService validationUrlMappingService;
	private RedisUrlMappingService redisUrlMappingService;
	
	@Value("${route.url}")
	private String routeUrl;

	public UrlMappingService(UrlMappingRepository urlMappingRepository, UrlMappingClickService urlMappingClickService,
			ShortUrlGenerator shortUrlGenerator, ValidationRedirectUrlService validationRedirectUrlService, ValidationUrlMappingService validationUrlMappingService
			,RedisUrlMappingService redisUrlMappingService) {
		this.urlMappingRepository = urlMappingRepository;
		this.urlMappingClickService = urlMappingClickService;
		this.shortUrlGenerator = shortUrlGenerator;
		this.validationRedirectUrlService = validationRedirectUrlService;
		this.validationUrlMappingService = validationUrlMappingService;
		this.redisUrlMappingService = redisUrlMappingService;
	}
	
	public UrlMappingDTO saveUrlMapping(OriginalUrlDTO originalUrlRecord, String language) throws Exception {
		logger.info("[URL SERVICE] Iniciando validação para geração de um url curto");
		validationUrlMappingService.executeValidations(originalUrlRecord.url(), language);

		logger.info("[URL SERVICE] Gerando url curto para url: " + originalUrlRecord.url());
		String shortUrl = generateShortUrl(originalUrlRecord.url());
		
		UrlMappingDTO urlMappingDTO = new UrlMappingDTO(originalUrlRecord.url(), shortUrl);
		
		logger.info("[URL SERVICE] Salvando registro no banco");
		UrlMappingDTO urlMappingSaveDTO = urlMappingRepository.save(urlMappingDTO.parseUrlMapping())
				.toUrlMappingDTO()
				.withConcatPrefixUrl(routeUrl);
		
		logger.info("[URL SERVICE] Processamento finalizado, gerando url curta");
		return urlMappingSaveDTO;
	}
	
	public String redirectUrl(String shortUrl, String language) throws Exception {
		logger.info("[URL SERVICE] Verificando se já existe registro no redis");
		Optional<String> originalUrlFromCache = redisUrlMappingService.getOriginalUrlFromCache(shortUrl);
		if(originalUrlFromCache.isPresent())
			return originalUrlFromCache.get();
		
		logger.info("[URL SERVICE]  Iniciando validação para short url");
		validationRedirectUrlService.executeValidations(shortUrl, language);
		
		UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
		
		logger.info("[URL SERVICE] Salvando registro no cache");
		redisUrlMappingService.saveUrlMappingInCache(urlMapping);
		
		logger.info("[URL SERVICE]  Registrando click na short Url: " + shortUrl);
		urlMappingClickService.registerClick(urlMapping);
		
		logger.info("[URL SERVICE] Redirecionando para a url: " +  urlMapping.getOriginalUrl());
		return urlMapping.getOriginalUrl();
	}
	
	public UrlMapping findByShortUrl(String shortUrl) {
		return urlMappingRepository.findByShortUrl(shortUrl);
	}
	
	public boolean isExistShortUrl(String shortUrl) {
		return urlMappingRepository.existsByShortUrl(shortUrl);
	}
	
	public String generateShortUrl(String originalUrl) {
		String shortUrl = shortUrlGenerator.generateRandomShortUrl();

		while (isExistShortUrl(shortUrl)) {
			logger.info("[URL SERVICE]  Foi identificado um shortUrl com o mesmo valor, gerando um novo shortUrl");
			shortUrl = shortUrlGenerator.generateRandomShortUrl();
		}
		
		return shortUrl;
	}
}
