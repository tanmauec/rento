package com.rento.core.exceptions;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionConstants {

    //exception context keys

    //set to true if exception is retryable
    public static final String IS_RETRYABLE = "isRetryable";

    //Use this key to provide root cause of an exception
    public static final String CAUSE = "cause";

    //validation errors
    public static final String INVALID_PHONENUMBER_LENGTH = "INVALID_PHONENUMBER_LENGTH";
}
