package com.rento.core.constants;

import lombok.Getter;
import okhttp3.MediaType;

public final class Constants {
    public static final String TENANT_ID_PREFIX = "TEN";
    public static final String LEASE_ID_PREFIX = "LEAS";
    public static final String OWNER_ID_PREFIX = "OWN";
    public static final String PROP_ID_PREFIX = "PROP";
    public static final String MANAGER_ID_PREFIX = "MANG";
    public static final String PAYMENT_ID_PREFIX = "TXN";
    public static final String INDIA_COUNTRY_CODE = "91";

    /**
     * The constant HEADER_COUNTRY_CODE.
     */
    public static final String HEADER_COUNTRY_CODE = "x-country-code";
    /**
     * The constant HEADER_USER_ID.
     */
    public static final String HEADER_USER_ID = "x-auth-user";
    /**
     * The constant HEADER_SESSION_ID.
     */
    public static final String HEADER_SESSION_ID = "x-session-id";
    /**
     * The constant HEADER_LANGUAGE.
     */
    public static final String HEADER_LANGUAGE = "x-language";
    /**
     * The constant HEADER_ORIGIN.
     */
    public static final String HEADER_ORIGIN = "origin";
    /**
     * The constant HEADER_FORWARDED_FOR.
     */
    public static final String HEADER_FORWARDED_FOR = "x-forwarded-for";
    /**
     * The constant HEADER_ACCESS_TOKEN.
     */
    public static final String HEADER_ACCESS_TOKEN = "x-access-token";
    /**
     * The constant HEADER_AUTH_ROLES.
     */
    public static final String HEADER_AUTH_ROLES = "x-auth-roles";
    /**
     * The constant HEADER_AUTH_TENANT.
     */
    public static final String HEADER_AUTH_TENANT = "x-auth-tenant";
    /**
     * The constant HEADER_AUTH_APPLICATION.
     */
    public static final String HEADER_AUTH_APPLICATION = "x-auth-application";
    /**
     * The constant HEADER_REFRESH_TOKEN.
     */
    public static final String HEADER_REFRESH_TOKEN = "x-refresh-token";
    /**
     * The constant AUTHORIZATION.
     */
    public static final String AUTHORIZATION = "authorization";
    /**
     * The constant LANGUAGE.
     */
    public static final String LANGUAGE = "language";
    /**
     * The constant COUNTRY_CODE.
     */
    public static final String COUNTRY_CODE = "country_code";
    /**
     * The constant STATUS.
     */
    public static final String STATUS = "status";
    /**
     * The constant EN.
     */
    public static final String EN = "en";
    /**
     * The constant VIDEO_LINK.
     */
    public static final String VIDEO_LINK = "http://numenhealth.com/join/";
    /**
     * The constant FILE_UPLOAD_SUCCESS.
     */
    public static final String FILE_UPLOAD_SUCCESS = "Profile image uploaded successfully";
    /**
     * The constant FILE_UPLOADED_SUCCESS.
     */
    public static final String FILE_UPLOADED_SUCCESS = "File uploaded successfully";
    /**
     * The constant CONTENT_TYPE_KEY.
     */
    public static final String CONTENT_TYPE_KEY = "Content-type";
    /**
     * The constant CONTENT_TYPE_JSON.
     */
    public static final String CONTENT_TYPE_JSON = "application/json";
    /**
     * The constant MEDIA_TYPE_JSON.
     */
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse(CONTENT_TYPE_JSON);
    /**
     * The constant QUICKBLOX_API_BASE_PATH.
     */
    public static final String QUICKBLOX_API_BASE_PATH = "https://api.quickblox.com";

    public static final String AMAZON_S3_BASE_PATH = ".s3.ap-south-1.amazonaws.com/";

    /**
     * The enum Response status.
     */
    public enum RESPONSE_STATUS {
        /**
         *Success response status.
         */
        success,
        /**
         *Failure response status.
         */
        failure
    }

    /**
     * The enum Entity states.
     */
    @Getter
    public enum ENTITY_STATES {
        /**
         *Created entity states.
         */
        CREATED,
        /**
         *Active entity states.
         */
        ACTIVE,
        /**
         *Deleted entity states.
         */
        DELETED
    }

    /**
     * The enum Notification states.
     */
    @Getter
    public enum NOTIFICATION_STATES {
        /**
         *Read notification states.
         */
        READ,
        /**
         *Unread notification states.
         */
        UNREAD
    }
}
