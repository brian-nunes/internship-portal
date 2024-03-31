package com.internship.portal.microservices.commons.service;

import com.internship.portal.microservices.commons.config.SessionRedisSerializer;
import com.internship.portal.microservices.commons.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

@Service
public class SessionService {

    public Session decodeSessionData(String compressedSessionData) {
        try {
            return new SessionRedisSerializer().deserialize(decompress(Base64.getDecoder().decode(compressedSessionData)));
        } catch (IllegalArgumentException | SerializationException | IOException e) {
            throw new RuntimeException("Error decompressing Base64 string to object", e);
        }
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
