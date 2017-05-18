package com.boot.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

	@Autowired
    private StringRedisTemplate redisTemplate;

	@Bean
	public CacheManager cacheManager(){
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		return rcm;
	}
}
