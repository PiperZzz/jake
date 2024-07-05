package spring.code.jake.myprojects.product.config;

import org.springframework.cache.annotation.*;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.*;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.CacheResolver;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class ProductCacheConfig implements CachingConfigurer {

    @Bean
    @Primary
    public CacheManager concurrentCacheManager() {
        return new ConcurrentMapCacheManager("myProducts");
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("myProductLists");
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.MINUTES));
        return caffeineCacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(concurrentCacheManager());
    }
}
