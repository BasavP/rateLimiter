package com.learn.ratelimiter.areaCalculator;

import lombok.Data;

@Data
public class AppException extends RuntimeException{

   private String message;
}
