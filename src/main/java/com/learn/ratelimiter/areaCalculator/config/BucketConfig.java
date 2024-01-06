package com.learn.ratelimiter.areaCalculator.config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BucketConfig {

    /*Method to config bucket and return the pojo*/
    @Bean
    public Bucket getBucket() {
        Refill intervally = Refill.intervally(1, Duration.ofSeconds(30));
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(1, intervally))
                .build();
    }
}
