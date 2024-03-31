package com.internship.portal.gateway.service;

import com.internship.portal.auth.commons.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Slf4j
public class AuthFilterFactory implements GatewayFilterFactory<AuthFilterFactory.Config> {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthFilterFactory.class);

    private final String SESSION_DATA = "SessionData";

    @Autowired
    private AuthService authService;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            List<String> authorizationHeaders = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
            log.info("Authorization Headers: {}", authorizationHeaders);
            if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No access token"));
            }

            String accessToken = authorizationHeaders.get(0);
            if(!authService.validateAccessToken(accessToken)){
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token"));
            }

            List<String> sessionHeaders = request.getHeaders().get(SESSION_DATA);
            log.info("Session Headers: {}", sessionHeaders);
            if (sessionHeaders != null && !sessionHeaders.isEmpty()) {
                String sessionData = sessionHeaders.get(0);
                if(authService.validateSessionDataWithToken(sessionData, accessToken)){
                    log.info("Session validated");
                    return chain.filter(exchange);
                } else {
                    return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid session data"));
                }
            } else {
                String compressedSessionData = authService.generateCompressedSessionDataFromAccessToken(accessToken);
                ServerHttpRequest newRequest = exchange.getRequest()
                        .mutate()
                        .header(SESSION_DATA, compressedSessionData)
                        .build();

                ServerWebExchange mutatedExchange = exchange.mutate().request(newRequest).build();

                return chain.filter(mutatedExchange);
            }
        };
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
        // You can add any configuration properties here if needed
    }
}
