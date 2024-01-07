package com.learn.ratelimiter;

import com.learn.ratelimiter.areaCalculator.config.RateLimitInterceptor;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;

@SpringBootApplication
public class RatelimiterApplication implements WebMvcConfigurer {


	@Autowired
	private RateLimitInterceptor rateLimitInterceptor;

	//list all the caching provider

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/rectangle2");
	}

	public static void main(String[] args) {
		SpringApplication.run(RatelimiterApplication.class, args);
	}

}
