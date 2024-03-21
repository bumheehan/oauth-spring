package xyz.bunda.oauthspring.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    @CacheEvict(cacheNames = "user", key = "#username")
    @Transactional
    public Long save(String username) {
        return userRepository.save(new User(username)).getId();
    }

    @Cacheable(cacheNames = "user", key = "#username")
    @Transactional
    public Long findIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow();
    }

}
