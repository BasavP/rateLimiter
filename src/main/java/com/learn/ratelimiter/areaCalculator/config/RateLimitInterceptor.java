package com.learn.ratelimiter.areaCalculator.config;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Data
@Component
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor{

//    @Autowired
    private final Bucket bucket;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(bucket.tryConsume(1)) {
            return true;
        } else {
            response.setStatus(429);
            response.addHeader("message", "too many requests");
            response.setContentType("application/json");
            response.addHeader("remaining-tokens", String.valueOf(bucket.getAvailableTokens()));
            log.info("too many requests! rate limit exceeded");
            return false;
        }
    }
}
