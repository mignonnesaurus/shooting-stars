package com.shootingstars.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shootingstars.exceptions.ShootingStarsException;

import java.io.IOException;
import java.util.List;

public class JsonReader {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructType(clazz);
        try {
            return getObjectMapper().readValue(json, javaType);
        } catch (IOException e) {
            throw ShootingStarsException.exception("Error while reading JSON", e);
        }
    }


    public static <T> List<T> fromJsonList(String jsonArray, Class<T> clazz) {
        final CollectionType javaType = getObjectMapper().getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return getObjectMapper().readValue(jsonArray, javaType);
        } catch (IOException e) {
            throw ShootingStarsException.exception("Error while reading JSON", e);
        }
    }

    private static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
