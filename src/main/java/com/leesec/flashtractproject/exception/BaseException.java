package com.leesec.flashtractproject.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private final String code;

    private final HttpStatus httpStatus;

    public BaseException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

//    public static AppException new400Exception(String code, String message) {
//        return new AppException(code, message, HttpStatus.BAD_REQUEST);
//    }
}
