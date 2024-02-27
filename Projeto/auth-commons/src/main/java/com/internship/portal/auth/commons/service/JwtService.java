package com.internship.portal.auth.commons.service;

import com.internship.portal.auth.commons.config.AuthCommonsConfig;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtService {
    @Autowired
    private AuthCommonsConfig authCommonsConfig;

    public String generateToken(String sessionHash) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (authCommonsConfig.getSessionDuration()*60000L));
        return Jwts.builder()
                .setExpiration(expiryDate)
                .setIssuedAt(now)
                .setSubject(sessionHash)
                .signWith(Keys.hmacShaKeyFor(authCommonsConfig.getJwtSecret().getBytes()))
                .compact();
    }

    public String getSessionHashFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(authCommonsConfig.getJwtSecret().getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getSessionHashFromToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Error validating token: ", e);
            return false;
        }
    }
}
