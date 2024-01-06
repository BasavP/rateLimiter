package com.learn.ratelimiter.areaCalculator.config;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Data
@Component
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor{

//    @Autowired
    private final Bucket bucket;
    private RedissonClient redisson = Redisson.create();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RMap<String,String> map = redisson.getMap("map");
        /*
        check if the request has  a user Id in the header only then proceed
        * */
        String userId = request.getHeader("x-user-id");

        if( !StringUtils.hasLength(userId)) {
            response.setStatus(400);
            response.addHeader("message", "user id not found");
            response.setContentType("application/json");
            log.info("user id not found");
            return false;
        }
        if(bucket.tryConsume(1)) {
            return true;
        } else {

            map.put(userId, request.getRequestURI());

            response.setStatus(429);
            response.addHeader("message", "too many requests");
            response.setContentType("application/json");
            response.addHeader("remaining-tokens", String.valueOf(bucket.getAvailableTokens()));
            log.info("too many requests! rate limit exceeded");
            log.info("remaining tokens: " + bucket.getAvailableTokens());
            /*print the contents of the map*/
            map.entrySet().forEach(entry -> {
                log.info(entry.getKey() + " " + entry.getValue());
            });
            return false;
        }
    }
}
