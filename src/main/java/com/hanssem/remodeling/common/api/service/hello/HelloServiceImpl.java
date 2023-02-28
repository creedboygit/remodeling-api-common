package com.hanssem.remodeling.common.api.service.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public String testCacheable(String value) {

        log.debug("#### TestCacheable: {}", value);
        return value;
    }

    @Override
    public void testCacheEvictAll() {

    }
}
