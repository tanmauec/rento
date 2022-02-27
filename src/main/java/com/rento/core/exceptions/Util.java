package com.rento.core.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {

    public static Throwable getRootCause(Exception throwable) {
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        if (Objects.isNull(rootCause)) {
            rootCause = throwable;
        }
        return rootCause;
    }

}
