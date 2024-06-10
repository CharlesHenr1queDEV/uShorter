package br.com.ushorter.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import br.com.ushorter.model.UrlMapping;

@Service
public class RedisUrlMappingService {
	
    private static final Logger logger = LoggerFactory.getLogger(RedisUrlMappingService.class);
    private static final String URL_KEY_PREFIX = "url::";

	private RedisTemplate<String, UrlMapping> redisTemplate;
	private UrlMappingClickService urlMappingClickService;

	public RedisUrlMappingService(RedisTemplate<String, UrlMapping> redisTemplate, UrlMappingClickService urlMappingClickService) {
		this.redisTemplate = redisTemplate;
		this.urlMappingClickService = urlMappingClickService;
	}
	
	public void saveUrlMappingInCache(UrlMapping urlMapping) {
        try {
            redisTemplate.opsForValue().set(URL_KEY_PREFIX + urlMapping.getShortUrl(), urlMapping, 1, TimeUnit.DAYS);
            logger.info("Mapeamento de URL salvo no cache para shortUrl: {}", urlMapping.getShortUrl());
        } catch (Exception e) {
            logger.error("Erro ao salvar o mapeamento de URL no cache para shortUrl: {}", urlMapping.getShortUrl(), e);
        }
    }

    public Optional<String> getOriginalUrlFromCache(String shortUrl) {
        try {
            UrlMapping urlMappingCache = redisTemplate.opsForValue().get(URL_KEY_PREFIX + shortUrl);

            if (urlMappingCache != null) {
                urlMappingClickService.registerClick(urlMappingCache);
                logger.info("Foi encontrado cache para shortUrl: {}", shortUrl);
                return Optional.of(urlMappingCache.getOriginalUrl());
            } else {
                logger.info("Não foi encontrado cache para shortUrl: {}", shortUrl);
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Erro ao recuperar o mapeamento de URL do cache para shortUrl: {}", shortUrl, e);
            return Optional.empty();
        }
    }
}
