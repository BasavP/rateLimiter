package com.learn.ratelimiter.areaCalculator.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
}
