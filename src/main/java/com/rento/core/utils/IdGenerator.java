package com.rento.core.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class IdGenerator {

    private static final int USER_ID_SUFFIX_SIZE = 10;
    public static String generateUUID(String prefix) {
        return prefix + UUID.randomUUID().toString();
    }

    public static String generateNumericId(String prefix) {
        return prefix + RandomStringUtils.randomNumeric(10);
    }
}
