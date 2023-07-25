package com.example.UnderTheSea_Server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Contact;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User")
@JsonIgnoreProperties(ignoreUnknown =true)
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImgUrl;

    @Column(name = "character_id", nullable = true)
    private Long characterId;

    @Column(name = "character_name", nullable = true)
    private String characterName;

    @Column(nullable = true)
    private Long mileage;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private UserStatus status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Builder
    public User(Long user_id, String email, String nickname, String profileImgUrl, Long character_id, String character_name, Long mileage, UserStatus status, Date created_at, Date updated_at) {
        this.userId = user_id;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
        this.characterId = character_id;
        this.characterName = character_name;
        this.mileage = mileage;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    /*
    @JsonIgnoreProperties(ignoreUnknown =true)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();
     */

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        /*
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
         */
    }

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public String getUsername() {
        return userId.toString();
    }

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnoreProperties(ignoreUnknown =true)
    @Override
    public boolean isEnabled() {
        return true;
    }
}