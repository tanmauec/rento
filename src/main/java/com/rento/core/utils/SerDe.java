package com.rento.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.rento.core.exceptions.ErrorCode;
import com.rento.core.exceptions.RentoServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Object mapper global access
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SerDe {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static void init() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String writeValueAsString(Object value) {
        return writeValueAsString(mapper(), value);
    }

    public static String writeValueAsString(ObjectMapper objectMapper, Object value) {
        try {
            if(value == null) {
                return null;
            }
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            log.error("Error while serializing object" + value, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static byte[] writeValueAsBytes(Object value) {
        return writeValueAsBytes(mapper(), value);
    }

    public static byte[] writeValueAsBytes(ObjectMapper objectMapper, Object value) {
        try {
            if(value == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(value);
        } catch (Exception e) {
            log.error("Error while serializing object" + value, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T readValue(String value, Class<T> valueType) {
        return readValue(mapper(), value, valueType);
    }

    public static <T> T readValue(ObjectMapper objectMapper, String value, Class<T> valueType) {
        try {
            if(value == null) {
                return null;
            }
            return objectMapper.readValue(value, valueType);
        } catch (Exception e) {
            log.error("Error while deserializing object" + value + " class:" + valueType, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T readValue(byte[] value, Class<T> valueType) {
        return readValue(mapper(), value, valueType);
    }

    public static <T> T readValue(ObjectMapper objectMapper, byte[] value, Class<T> valueType) {
        try {
            if(value == null) {
                return null;
            }
            return objectMapper.readValue(value, valueType);
        } catch (Exception e) {
            log.error("Error while deserializing byte[]" + new String(value) + " class:" + valueType, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T readValue(byte[] value, TypeReference valueTypeRef) {
        return readValue(mapper(), value, valueTypeRef);
    }

    public static <T> T readValue(ObjectMapper objectMapper, byte[] value, TypeReference valueTypeRef) {
        try {
            if(value == null) {
                return null;
            }
            return (T) objectMapper.readValue(value, valueTypeRef);
        } catch (Exception e) {
            log.error("Error while deserializing byte[]" + new String(value) + " valueTypeRef:" + valueTypeRef, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T readValue(String value, TypeReference valueTypeRef) {
        return readValue(mapper(), value, valueTypeRef);
    }

    public static <T> T readValue(ObjectMapper objectMapper, String value, TypeReference valueTypeRef) {
        try {
            if(value == null) {
                return null;
            }
            return (T) objectMapper.readValue(value, valueTypeRef);
        } catch (Exception e) {
            log.error("Error while deserializing value" + value + " valueTypeRef:" + valueTypeRef, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }


    public static <T> T readValue(InputStream src, Class<T> valueType) {
        return readValue(mapper(), src, valueType);
    }

    public static <T> T readValue(ObjectMapper objectMapper, InputStream src, Class<T> valueType) {
        try {
            if(src == null) {
                return null;
            }
            return objectMapper.readValue(src, valueType);
        } catch (Exception e) {
            log.error("Error while deserializing inputStream valueType:" + valueType, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return convertValue(mapper(), fromValue, toValueType);
    }

    public static <T> T convertValue(ObjectMapper objectMapper, Object fromValue, Class<T> toValueType) {
        try {
            if(fromValue == null) {
                return null;
            }
            return objectMapper.convertValue(fromValue, toValueType);
        } catch (Exception e) {
            log.error("Error while deserializing fromValue" + fromValue + " toValueType:" + toValueType, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T  convertValue(ObjectMapper objectMapper, Object fromValue, TypeReference valueTypeRef) {
        try {
            if(fromValue == null) {
                return null;
            }
            return (T) objectMapper.convertValue(fromValue, valueTypeRef);
        } catch (Exception e) {
            log.error("Error while deserializing fromValue" + fromValue + " toValueType:" + valueTypeRef, e);
            throw RentoServiceException.propagate(ErrorCode.JSON_ERROR, e);
        }
    }

    public static <T> T convertValue(Object fromValue, TypeReference valueTypeRef) {
        return convertValue(mapper(), fromValue, valueTypeRef);
    }

    private static ObjectMapper mapper() {
        Preconditions.checkNotNull(mapper, "Please call SerDe.init(mapper) to set mapper");
        return mapper;
    }


    public static <T> T getResource(String path, Class<T> klass) throws IOException {
        mapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        final InputStream data = SerDe.class.getClassLoader().getResourceAsStream(path);
        return mapper().readValue(data, klass);
    }

    public static String getResourceAsString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
