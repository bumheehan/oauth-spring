package xyz.bunda.oauthspring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitCache {
    @Cacheable(cacheNames = "testcache", key = "getMethodName()")
    public String cachetest() {
        log.info("inner");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "aa";
    }

    @Cacheable(cacheNames = "exercise", key = "getMethodName()")
    public String exercise() {
        log.info("inner");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "aa";
    }
}