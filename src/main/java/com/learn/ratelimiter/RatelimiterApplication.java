package com.learn.ratelimiter;

import com.learn.ratelimiter.areaCalculator.config.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RatelimiterApplication implements WebMvcConfigurer {


	@Autowired
	private RateLimitInterceptor rateLimitInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/rectangle2");
	}

	public static void main(String[] args) {
		SpringApplication.run(RatelimiterApplication.class, args);
	}

}
