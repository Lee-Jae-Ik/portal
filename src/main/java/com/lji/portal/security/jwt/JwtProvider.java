package com.lji.portal.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JwtProvider
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.accessTokenKey}")
    private static String ACCESSTOKEN_SECRETE_KEY;

    @Value("${jwt.accessTokenKey}")
    private static String REFRESHTOKEN_SECRETE_KEY;

    @Value("${jwt.time.accessToken")
    private static long accessTokenValidityInMilliseconds;

    @Value("${jwt.time.accessToken")
    private static long refreshTokenValidityInMilliseconds;




    public String createAccessToken(Long userId, String roleName) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS512");

        return Jwts.builder()
                .setHeader(headers)
                .setSubject(String.valueOf(userId))
                .setId(String.valueOf(UUID.randomUUID()))
                .claim(AUTHORITIES_KEY, roleName)
                .signWith(SignatureAlgorithm.HS512, ACCESSTOKEN_SECRETE_KEY)
                .setExpiration(createTokenExpireDate(true))
                .compact();
    }

    public String createRefreshToken(Long userId, String roleName) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS512");

        return Jwts.builder()
                .setHeader(headers)
                .setSubject(String.valueOf(userId))
                .setId(String.valueOf(UUID.randomUUID()))
                .claim(AUTHORITIES_KEY, roleName)
                .signWith(SignatureAlgorithm.HS512, REFRESHTOKEN_SECRETE_KEY)
                .setExpiration(createTokenExpireDate(false))
                .compact();
    }

    public Date createTokenExpireDate(boolean isAccessToken) {
        // 만료일 생성
        long now = (new Date()).getTime();
        long tokenValidityInMilliseconds = isAccessToken ? accessTokenValidityInMilliseconds : refreshTokenValidityInMilliseconds;
        return new Date(now + tokenValidityInMilliseconds);
    }

}
