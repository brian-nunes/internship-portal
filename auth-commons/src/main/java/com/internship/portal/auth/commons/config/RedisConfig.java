package com.internship.portal.auth.commons.config;

import com.internship.portal.auth.commons.model.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.*;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Session> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Session> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new SessionRedisSerializer());
        return redisTemplate;
    }
}
