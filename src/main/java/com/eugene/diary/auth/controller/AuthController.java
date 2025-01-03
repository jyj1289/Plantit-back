package com.eugene.diary.auth.controller;

import com.eugene.diary.auth.controller.dto.response.TokenResponse;
import com.eugene.diary.auth.service.GoogleAuthService;
import com.eugene.diary.auth.service.TokenService;
import com.eugene.diary.shared.response.CommonResponse;
import com.eugene.diary.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthService googleAuthService;
    private final TokenService tokenService;

    @GetMapping("/google/link")
    public SingleCommonResponse<String> getGoogleAuthUrl() {
        return CommonResponse.success(
                googleAuthService.getGoogleAuthLink()
        );
    }

    @PostMapping("/google")
    public SingleCommonResponse<TokenResponse> authWithGoogle(@RequestParam String code) {
        return CommonResponse.success(
            googleAuthService.googleAuth(code)
        );
    }

    @PostMapping("/refresh")
    public SingleCommonResponse<TokenResponse> refreshAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return CommonResponse.success(
                tokenService.refreshAccessToken(refreshToken)
        );
    }

}
