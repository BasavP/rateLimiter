package com.learn.ratelimiter.areaCalculator.DTO;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@Builder
public class BaseResponse<T> extends ResponseEntity {

    int httpStatusCode;
    String message;
    int errorCode;
    T payload;

    //Default constructor
    public BaseResponse() {
        super(HttpStatus.OK);
    }

    //All args constructor

    public BaseResponse(int httpStatusCode, String message, int errorCode, T payload) {
        super(HttpStatus.OK);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.errorCode = errorCode;
        this.payload = payload;
    }
    public BaseResponse(HttpStatusCode status) {
        super(status);
    }

    public BaseResponse(Object body, HttpStatusCode status) {
        super(body, status);
    }

    public BaseResponse(MultiValueMap headers, HttpStatusCode status) {
        super(headers, status);
    }

    public BaseResponse(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public BaseResponse(Object body, MultiValueMap headers, HttpStatusCode statusCode) {
        super(body, headers, statusCode);
    }
}
