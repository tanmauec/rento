package com.rento.core.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode is a combination of following fields:
 * 1. HttpResponse.Status - Http code (eg. 500,400,401 etc) that should be returned for this error.
 * 2. Error Message - to give actual reason of error.
 */
public enum  ErrorCode {

    //login related errorcodes
    PHONENUMBER_NOT_REGISTERED(HttpStatus.BAD_REQUEST, "PHONENUMBER_NOT_REGISTERED"),
    WEAK_PASSWORD_REQUEST(HttpStatus.BAD_REQUEST, "WEAK_PASSWORD_REQUEST"),
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "INCORRECT_PASSWORD"),
    INVALID_EMAILID(HttpStatus.BAD_REQUEST, "INVALID_EMAILID"),
    INCORRECT_OTP(HttpStatus.BAD_REQUEST, "INCORRECT_OTP"),
    INVALID_COUNTRYCODE(HttpStatus.BAD_REQUEST, "INVALID_COUNTRYCODE"),
    INVALID_PHONENUMBER(HttpStatus.BAD_REQUEST, "INVALID_PHONENUMBER"),

    //User related error codes
    TENANT_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER_ALREADY_EXIST"),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR"),
    DAO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DAO_ERROR"),
    RMQ_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "RMQ_ERROR"),
    PROVIDER_INITIALIZATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PROVIDER_INITIALIZATION_ERROR"),
    PROVIDER_SHUTDOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PROVIDER_SHUTDOWN_ERROR"),
    JSON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON_ERROR"),
    COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMUNICATION_ERROR"),
    HYSTRIX_COMMAND_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "HYSTRIX_COMMAND_EXCEPTION"),
    SERVICE_STARTUP_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVICE_STARTUP_ERROR"),
    SERVICE_STOP_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVICE_STOP_ERROR"),
    CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "CLIENT_ERROR"),
    UNSUPPORTED_OPERATION(HttpStatus.INTERNAL_SERVER_ERROR, "UNSUPPORTED_OPERATION");

    @Getter
    private HttpStatus status;

    @Getter
    private String code;

    ErrorCode(HttpStatus httpStatus, String code) {
        this.status = httpStatus;
        this.code = code;
    }
}
