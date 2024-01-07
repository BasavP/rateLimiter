package com.learn.ratelimiter.areaCalculator.config;

import com.learn.ratelimiter.areaCalculator.listener.RqueueListener;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Frequency;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RQueue;
import org.redisson.api.RSetCache;
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
    private final BucketConfig bucketConfig;
    private RedissonClient redisson = Redisson.create();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RQueue<String> queue = redisson.getQueue("requestQueue");
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

        Bucket bucketForUserId = getBucketForUserId(userId);
        if(checkIfRateLimitExceeded(bucketForUserId)) {
            return true;
        } else {
            response.setStatus(429);
            response.addHeader("message", "too many requests");
            response.setContentType("application/json");
            response.addHeader("remaining-tokens", String.valueOf(bucketForUserId.getAvailableTokens()));
            log.info("too many requests! rate limit exceeded");
            log.info("remaining tokens: " + bucketForUserId.getAvailableTokens());
            return false;
        }
    }

    private Bucket getBucketForUserId(String userId) {
        Bucket bucket = bucketConfig.resolveBucket(userId);
        return bucket;
    }
    private boolean checkIfRateLimitExceeded(Bucket bucket) {
        return bucket.tryConsume(1);
    }
}
