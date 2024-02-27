package com.internship.portal.report.filter;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SessionDataFilter extends OncePerRequestFilter {

    private final String SESSION_DATA = "SessionData";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String sessionData = request.getHeader(SESSION_DATA);

        if (sessionData == null || sessionData.isEmpty()) {
            throw new BaseBusinessException("ERROR_LOGIN_0001", "SessionData header not present.", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (accessToken == null || accessToken.isEmpty()) {
            throw new BaseBusinessException("ERROR_LOGIN_0002", "Authorization header not present.", HttpStatus.UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }
}
