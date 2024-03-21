package xyz.bunda.oauthspring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import xyz.bunda.oauthspring.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String COGNITO_GROUPS = "cognito:groups";
    private final UserService userService;

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        List<String> roles = (List<String>) source.getClaims().get(COGNITO_GROUPS);

        log.info("???");
        // Create authorities from roles.
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());

        //유저정보 매핑
        String username = (String) source.getClaims().get("username");
        Long id;
        try {
            id = userService.findIdByUsername(username);
        } catch (Exception e) {
            id = userService.save(username);
        }

        log.info("id : " + id + ", username : " + username);

        return new JwtAuthenticationToken(source, authorities);
    }
}