package com.eugene.diary.auth.controller;

import com.eugene.diary.auth.controller.dto.response.TokenResponse;
import com.eugene.diary.auth.service.GoogleAuthService;
import com.eugene.diary.auth.service.TokenService;
import com.eugene.diary.shared.auth.AuthenticationPrincipal;
import com.eugene.diary.shared.response.CommonResponse;
import com.eugene.diary.shared.response.SingleCommonResponse;
import com.eugene.diary.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthService googleAuthService;
    private final TokenService tokenService;

    @GetMapping("/google/link")
    public SingleCommonResponse<String> getGoogleAuthUrl() {
        return CommonResponse.ok(
                googleAuthService.getGoogleAuthLink()
        );
    }

    @PostMapping("/google")
    public SingleCommonResponse<TokenResponse> authWithGoogle(@RequestParam String code) {
        return CommonResponse.ok(
            googleAuthService.googleAuth(code)
        );
    }

    @PostMapping("/refresh")
    public SingleCommonResponse<TokenResponse> refreshAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return CommonResponse.ok(
                tokenService.refreshAccessToken(refreshToken)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logout(@AuthenticationPrincipal User user) {
        tokenService.logout(user);
    }
}
