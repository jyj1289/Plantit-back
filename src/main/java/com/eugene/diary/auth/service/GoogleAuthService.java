package com.eugene.diary.auth.service;

import com.eugene.diary.auth.controller.dto.response.TokenResponse;
import com.eugene.diary.infrastructure.auth.google.GoogleAuthClient;
import com.eugene.diary.infrastructure.auth.google.GoogleInformationClient;
import com.eugene.diary.infrastructure.auth.google.dto.request.GoogleAuthRequest;
import com.eugene.diary.infrastructure.auth.google.dto.response.GoogleInformationResponse;
import com.eugene.diary.shared.config.properties.AuthProperties;
import com.eugene.diary.user.domain.User;
import com.eugene.diary.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GoogleAuthService {

    private final GoogleAuthClient googleAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final AuthProperties authProperties;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s&response_type=code&" + "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    public String getGoogleAuthLink() {
        return authProperties.getGoogleBaseUrl() + String.format(QUERY_STRING, authProperties.getGoogleClientId(), authProperties.getGoogleRedirectUri());
    }

    @Transactional
    public TokenResponse googleAuth(String code) {
        String accessToken = googleAuthClient
                .getAccessToken(createGoogleAuthRequest(code))
                .getAccessToken();

        GoogleInformationResponse information = googleInformationClient.getUserInformation("Bearer " + accessToken);

        String email = information.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            userRepository.save(new User(email, information.getName()));
        }

        return TokenResponse.builder()
                .accessToken(tokenService.generateAccessToken(email))
                .refreshToken(tokenService.generateRefreshToken(email))
                .build();
    }

    private GoogleAuthRequest createGoogleAuthRequest(String code) {
        return GoogleAuthRequest.builder()
                .code(code)
                .clientId(authProperties.getGoogleClientId())
                .redirectUri(authProperties.getGoogleRedirectUri())
                .clientSecret(authProperties.getGoogleClientSecret())
                .build();
    }
}
