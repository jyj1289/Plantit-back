package com.eugene.diary.auth.service;

import com.eugene.diary.auth.controller.dto.response.TokenResponse;
import com.eugene.diary.auth.domain.Token;
import com.eugene.diary.auth.domain.TokenRepository;
import com.eugene.diary.auth.domain.type.TokenType;
import com.eugene.diary.shared.config.properties.JwtProperties;
import com.eugene.diary.user.domain.User;
import com.eugene.diary.user.service.UserFacade;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtProperties jwtProperties;
    public final TokenRepository tokenRepository;
    public final UserFacade userFacade;

    public String generateAccessToken(String id) {
        return generateToken(id, TokenType.ACCESS_TOKEN,
                jwtProperties.getAccessExpirationTime());
    }

    public String generateRefreshToken(String id) {
        String token = generateToken(id, TokenType.REFRESH_TOKEN,
                jwtProperties.getRefreshExpirationTime());
        tokenRepository.save(
                Token.builder()
                        .id(id)
                        .token(token)
                        .build()
        );

        return token;
    }

    @Transactional(readOnly = true)
    public TokenResponse refreshAccessToken(String refreshToken) {
        validate(refreshToken);
        Token token = getToken(refreshToken);

        return TokenResponse.builder()
                .accessToken(token.getToken())
                .build();
    }

    private void validate(String token) {
        if (!Objects.equals(getType(token), TokenType.REFRESH_TOKEN.name())) {
            throw new IllegalArgumentException();
        }
    }

    private void validate(String expectedToken, String actualToken) {
        if (!Objects.equals(expectedToken, actualToken)) {
            throw new IllegalArgumentException();
        }
    }

    private Token getToken(String refreshToken) {
        String id = getId(refreshToken);
        Token token = tokenRepository.findById(id).orElseThrow();
        validate(refreshToken, token.getToken());

        return token;
    }

    private String generateToken(String id, TokenType type, Long expirationTime) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("type", type.name());

        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(getSigningKey(jwtProperties.getSecretKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public User getUser(String token) {
        return userFacade.getUser(getId(token));
    }

    public String getId(String token) {
        return extractAllClaims(token).get("id", String.class);
    }

    public String getType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

