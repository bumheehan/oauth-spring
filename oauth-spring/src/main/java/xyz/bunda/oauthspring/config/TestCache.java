package xyz.bunda.oauthspring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestCache {

    private final InitCache initCache;

    @GetMapping("test")
    public String init() {
        return initCache.cachetest();
    }

    @GetMapping("exercise")
    public String exercise() {
        return initCache.exercise();
    }


}
