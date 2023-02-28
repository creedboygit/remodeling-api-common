package com.hanssem.remodeling.common.api.service.hello;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface HelloService {

    @Cacheable(cacheManager = "baseCacheManager", cacheNames = "testCacheable", key = "#value")
    String testCacheable(String value);

    @CacheEvict(cacheManager = "baseCacheManager", cacheNames = "*", allEntries = true)
    void testCacheEvictAll();
}
