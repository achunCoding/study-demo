package cn.wycfight.study.caching.ttl.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring缓存配置
 */
@Configuration
@EnableCaching
public class SpringCachingConfig {


    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("hotels");
    }

}
