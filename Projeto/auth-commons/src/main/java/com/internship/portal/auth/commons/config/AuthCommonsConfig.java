package com.internship.portal.auth.commons.config;

import com.internship.portal.auth.commons.model.Session;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@RefreshScope
@Getter
@PropertySource("classpath:auth-application.properties")
public class AuthCommonsConfig {
    @Value("${session.duration}")
    private int sessionDuration;

    @Value("${jwt.secret}")
    private String jwtSecret;
}
