package com.internship.portal.auth.commons.service;

import com.internship.portal.auth.commons.config.AuthCommonsConfig;
import com.internship.portal.auth.commons.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate<String, Session> redisTemplate;

    @Autowired
    private AuthCommonsConfig authCommonsConfig;

    public String setValue(Session session){
        String sessionHash = String.valueOf(session.hashCode());
        log.info("Session hash: [{}], Session: [{}], Duration [{}]", sessionHash, session, authCommonsConfig.getSessionDuration());
        redisTemplate.opsForValue().set(sessionHash, session, authCommonsConfig.getSessionDuration(), TimeUnit.MINUTES);
        return sessionHash;
    }

    public Session getValue(String sessionHash){
        return redisTemplate.opsForValue().get(sessionHash);
    }

    public void deleteValue(String sessionHash){
        redisTemplate.delete(sessionHash);
    }

    public Boolean existsValue(String sessionHash){
        return redisTemplate.hasKey(sessionHash);
    }
}
