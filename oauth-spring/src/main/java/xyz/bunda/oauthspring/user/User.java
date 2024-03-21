package xyz.bunda.oauthspring.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "mapping_user", indexes = {@Index(name = "IDX_USER_NAME", columnList = "username", unique = true)})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "username", length = 36)
    private String username;

    public User(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }
        this.username = username;
    }
}
