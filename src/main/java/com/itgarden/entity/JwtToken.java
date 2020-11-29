package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/*
 * Created by Suresh Stalin on 10 / Nov / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "JWT_TOKEN")
public class JwtToken extends BaseObject {

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "access_token_expiration", nullable = false)
    private LocalDateTime accessTokenExpiration;

    @Column(name = "refresh_token_expiration", nullable = false)
    private LocalDateTime refreshTokenExpiration;
}
