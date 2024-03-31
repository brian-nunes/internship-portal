package com.internship.portal.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

@Configuration
public class FilterConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    @Order(-1)
    public GlobalFilter loggingFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // Log request details
            logRequest(request);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Log response details
                logResponse(request, exchange.getResponse());
            }));
        };
    }

    @Bean
    @Order(0)
    public AuthFilterFactory authFilterFactory(){
        return new AuthFilterFactory();
    }

    private void logRequest(ServerHttpRequest request) {
        LOGGER.info("Request {} Method: [{}] URL [{}] Headers: [{}] Body[{}]",
                request.getId(), request.getMethod(), request.getURI(), request.getHeaders(), request.getBody());
    }

    private void logResponse(ServerHttpRequest request, ServerHttpResponse response) {
        LOGGER.info("Response {} Status: [{}]", request.getId(), response.getStatusCode());
    }
}

