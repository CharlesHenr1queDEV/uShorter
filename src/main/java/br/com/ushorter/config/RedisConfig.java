package br.com.ushorter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import br.com.ushorter.model.UrlMapping;

@Configuration
public class RedisConfig {
	
	@Bean
	public RedisTemplate<String, UrlMapping> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, UrlMapping> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
}