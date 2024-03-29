package com.internship.portal.authentication.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Getter
public class AuthenticationProperties {
    @Value("${session.duration}")
    private int sessionDuration;

    @Value("${jwt.secret}")
    private String jwtSecret;
}
