package com.internship.portal.auth.commons.service;

import com.internship.portal.auth.commons.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class AuthService {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private JwtService jwtService;

    public String generateSession(String name, String documentNumber, String mail){
        log.info("Generating session for document [{}]", documentNumber);
        String sessionHash = sessionService.createSession(name, documentNumber, mail);
        log.info("Session Hash Generated for Document [{}]: [{}]", documentNumber, sessionHash);
        return sessionHash;
    }

    public String generateToken(String sessionHash){
        String token = jwtService.generateToken(sessionHash);
        log.info("Token Generated for Session Hash [{}]: [{}]", sessionHash, token);
        return token;
    }

    public Boolean validateSessionDataWithToken(String sessionData, String accessToken){
        Session session = sessionService.decodeSessionData(sessionData);
        log.info("Decoded sessionData: [{}]", session);
        String sessionHashFromToken = jwtService.getSessionHashFromToken(accessToken);
        log.info("sessionHashFromToken: [{}] | sessionHash: [{}]", sessionHashFromToken, session.hashCode());
        return sessionHashFromToken.equals(String.valueOf(session.hashCode())) && sessionService.existsSession(String.valueOf(session.hashCode()));
    }

    public Boolean validateAccessToken(String accessToken){
        return jwtService.validateToken(accessToken);
    }

    public void deleteSession(String accessToken){
        String sessionHash = jwtService.getSessionHashFromToken(accessToken);
        sessionService.deleteSession(sessionHash);
    }

    public String generateCompressedSessionData(String sessionHash){
        Session session = sessionService.getSession(sessionHash);
        log.info("Session to be compressed: [{}]", session);
        String sessionCompressed = sessionService.generateSessionData(session);
        log.info("Session compressed: [{}]", sessionCompressed);
        return sessionCompressed;
    }

    public String generateCompressedSessionDataFromAccessToken(String accessToken){
        String sessionHash = jwtService.getSessionHashFromToken(accessToken);
        log.info("SessionHash from accessToken: [{}]", sessionHash);
        Session session = sessionService.getSession(sessionHash);
        log.info("Session from sessionHash [{}]: [{}]", sessionHash, session);
        String sessionCompressed = sessionService.generateSessionData(session);
        log.info("Session compressed: [{}]", sessionCompressed);
        return sessionCompressed;
    }

    private Session decompressSessionData(String sessionData){
        Session session = sessionService.decodeSessionData(sessionData);
        log.info("Session decompressed: [{}]", session);
        return session;
    }
}
