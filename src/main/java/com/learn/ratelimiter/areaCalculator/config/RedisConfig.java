package com.learn.ratelimiter.areaCalculator.config;

import com.giffing.bucket4j.spring.boot.starter.config.cache.SyncCacheResolver;
import com.giffing.bucket4j.spring.boot.starter.config.cache.jcache.JCacheCacheResolver;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.cache.CacheManager;
import javax.cache.Caching;

@Configuration
public class RedisConfig {
    @Bean
    public Config config(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return config;
    }

    @Bean
    public CacheManager cacheManager(Config config) {
        CacheManager cacheManager = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider").getCacheManager();
        cacheManager.createCache("cache", RedissonConfiguration.fromConfig(config));
        return cacheManager;
    }
    @Bean
    ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache("cache"));
    }

    @Bean
    @Primary
    public SyncCacheResolver bucket4jCacheResolver(CacheManager cacheManager) {
        return new JCacheCacheResolver(cacheManager);
    }

}
