package com.rento.core.exceptions;

import com.google.common.collect.Maps;
import lombok.*;

import java.util.Map;

/**
 * Global exception object
 */
@Getter
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RentoServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    //If context of error requires info to be given for multiple keys, use this map.
    private final Map<String, Object> context;

    private final boolean isRetryable;

    @Builder
    private RentoServiceException(Throwable cause,
                                  ErrorCode errorCode,
                                  String message,
                                  Map<String, Object> context,
                                  boolean isRetryable) {
        super(message, cause);
        this.errorCode = errorCode;
        this.context = context;
        this.isRetryable = isRetryable;
    }

    private RentoServiceException(ErrorCode errorCode,
                                  Map<String, Object> context) {
        super();
        this.errorCode = errorCode;
        this.context = context;
        this.isRetryable = false;
    }

    public static RentoServiceException propagate(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof RentoServiceException) { return (RentoServiceException) cause; }

        return new RentoServiceException(cause, errorCode, cause.getMessage(), Maps.newHashMap(), false);
    }

    @Builder
    public static RentoServiceException error(ErrorCode errorCode, Map<String, Object> context) {
        return new RentoServiceException(errorCode, context);
    }

    @Builder
    public static RentoServiceException error(ErrorCode errorCode, String message, Map<String, Object> context, boolean isRetryable) {
        return RentoServiceException.builder()
                .errorCode(errorCode)
                .message(message)
                .context(context)
                .isRetryable(isRetryable)
                .build();
    }

}
