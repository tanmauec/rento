package com.rento.core.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericError {
    private ErrorCode errorCode;
    private String message;
}
