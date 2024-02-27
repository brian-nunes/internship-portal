package com.internship.portal.auth.commons.service;

import com.internship.portal.auth.commons.config.SessionRedisSerializer;
import com.internship.portal.auth.commons.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Service
@Slf4j
public class SessionService {
    @Autowired
    private RedisService redisService;

    public String createSession(String name, String documentNumber, String mail) {
        Session session = new Session(name, documentNumber, mail);
        log.info("Session built: [{}]", session);
        return redisService.setValue(session);
    }
    public Session getSession(String sessionHash){
        return redisService.getValue(sessionHash);
    }

    public void deleteSession(String sessionHash){
        redisService.deleteValue(sessionHash);
    }

    public Boolean existsSession(String sessionHash){
        return redisService.existsValue(sessionHash);
    }

    public String generateSessionData(Session session) {
        try {
            return Base64.getEncoder().encodeToString(compress(new SessionRedisSerializer().serialize(session)));
        } catch (SerializationException | IOException e) {
            throw new RuntimeException("Error compressing object to Base64 string", e);
        }
    }

    public Session decodeSessionData(String compressedSessionData) {
        try {
            return new SessionRedisSerializer().deserialize(decompress(Base64.getDecoder().decode(compressedSessionData)));
        } catch (IllegalArgumentException | SerializationException | IOException e) {
            throw new RuntimeException("Error decompressing Base64 string to object", e);
        }
    }

    private byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
            gzipOutputStream.write(data);
        }
        return outputStream.toByteArray();
    }

    private byte[] decompress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
             GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = gzipInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return outputStream.toByteArray();
    }
}
