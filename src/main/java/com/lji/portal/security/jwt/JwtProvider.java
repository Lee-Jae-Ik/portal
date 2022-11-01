package com.lji.portal.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;

/**
 * JwtProvider
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
@Slf4j
@Component
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${jwt.accessTokenKey}")
    private static String ACCESSTOKEN_SECRETE_KEY;

    @Value("${jwt.accessTokenKey}")
    private static String REFRESHTOKEN_SECRETE_KEY;

    @Value("${jwt.time.accessToken")
    private static long accessTokenValidityInMilliseconds;

    @Value("${jwt.time.accessToken")
    private static long refreshTokenValidityInMilliseconds;

    private Key accessTokenJwtKey;
    private Key refreshTokenJwtKey;

    @PostConstruct
    public void keyInitialize() {
        this.accessTokenJwtKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(ACCESSTOKEN_SECRETE_KEY));
        this.refreshTokenJwtKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(REFRESHTOKEN_SECRETE_KEY));
    }

    public String createAccessToken(Long userId, String roleName) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS512");

        return Jwts.builder()
                .setHeader(headers)
                .setSubject(String.valueOf(userId))
                .setId(String.valueOf(UUID.randomUUID()))
                .claim(AUTHORITIES_KEY, roleName)
                .signWith(SignatureAlgorithm.HS512, accessTokenJwtKey)
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
                .signWith(SignatureAlgorithm.HS512, refreshTokenJwtKey)
                .setExpiration(createTokenExpireDate(false))
                .compact();
    }

    public Date createTokenExpireDate(boolean isAccessToken) {
        // 만료일 생성
        long now = (new Date()).getTime();
        long tokenValidityInMilliseconds = isAccessToken ? accessTokenValidityInMilliseconds : refreshTokenValidityInMilliseconds;
        return new Date(now + tokenValidityInMilliseconds);
    }


    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(accessTokenJwtKey).parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid access signature.");
        } catch (ExpiredJwtException e) {
            log.info("Expired access token.");
        } catch (UnsupportedJwtException e) {
            log.info("An unsupported access token.");
        } catch (IllegalArgumentException e) {
            log.info("Invalid access token.");
        }
        return false;
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser().setSigningKey(refreshTokenJwtKey).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid refresh signature.");
        } catch (ExpiredJwtException e) {
            log.info("Expired refresh token.");
        } catch (UnsupportedJwtException e) {
            log.info("An unsupported refresh token.");
        } catch (IllegalArgumentException e) {
            log.info("Invalid refresh token.");
        }
        return false;
    }

    public Long getUserIdFromToken(String jwtToken) {
        JSONObject jsonObj = getJsonPayloadFromToken(jwtToken);
        if (jsonObj == null)
            return null;
        else {
            String sub = jsonObj.get("sub").toString();
            return Long.parseLong(sub);
        }
    }

    public JSONObject getJsonPayloadFromToken(String jwtToken) {

        JSONParser jsonParser = new JSONParser();

        if (jwtToken == null)
            return null;

        String[] base64Payload = jwtToken.split("\\.");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) jsonParser.parse(base64Payload[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);

        return null;
    }
}
