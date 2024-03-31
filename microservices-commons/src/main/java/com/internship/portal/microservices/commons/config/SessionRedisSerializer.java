package com.internship.portal.microservices.commons.config;

import com.internship.portal.microservices.commons.model.Session;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

public class SessionRedisSerializer implements RedisSerializer<Session> {
    @Override
    public byte[] serialize(Session value) throws SerializationException {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bytes);
            os.writeObject(value);
            return bytes.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Session deserialize(byte[] bytes) throws SerializationException {
        try {
            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (Session) is.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
