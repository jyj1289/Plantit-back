package com.eugene.diary.auth.controller;

import com.eugene.diary.auth.controller.dto.response.TokenResponse;
import com.eugene.diary.auth.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @GetMapping("/google/link")
    public String getGoogleAuthUrl() {
        return googleAuthService.getGoogleAuthLink();
    }

    @PostMapping("/google")
    public TokenResponse authWithGoogle(@RequestParam String code) {
        return googleAuthService.googleAuth(code);
    }
}
