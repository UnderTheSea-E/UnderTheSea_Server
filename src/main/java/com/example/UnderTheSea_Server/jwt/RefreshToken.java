package com.example.UnderTheSea_Server.jwt;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "T_REFRESH_TOKEN")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFRESH_TOKEN_ID", nullable = false)
    private Long refreshTokenId;

    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private String keyId;

    @Builder
    public RefreshToken(Long refreshTokenId, String refreshToken, String keyId) {
        this.refreshTokenId = refreshTokenId;
        this.refreshToken = refreshToken;
        this.keyId = keyId;
    }
}
