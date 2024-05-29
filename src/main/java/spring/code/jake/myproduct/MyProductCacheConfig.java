package spring.code.jake.myproduct;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.*;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class MyProductCacheConfig {

    @Bean
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
}
