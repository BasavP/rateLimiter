package com.learn.ratelimiter.areaCalculator.config;


import io.github.bucket4j.*;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class BucketConfig {
    @Autowired
    public ProxyManager buckets;
//    Method to config bucket and return the pojo
/*    @Bean
    public Bucket getBucket() {
        Refill intervally = Refill.intervally(1, Duration.ofSeconds(30));
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(1, intervally))
                .build();
    }*/

    public Bucket resolveBucket(String userId){
        return  buckets.builder().build(userId,getConfigSupplierForUser(userId));
    }

    private Supplier<BucketConfiguration> getConfigSupplierForUser(String userId){
return () -> {
            Refill refill = Refill.intervally(1, Duration.ofSeconds(30));
            Bandwidth limit = Bandwidth.classic(1, refill);
            return Bucket4j.configurationBuilder()
                    .addLimit(limit)
                    .buildConfiguration();
        };
    }




}
