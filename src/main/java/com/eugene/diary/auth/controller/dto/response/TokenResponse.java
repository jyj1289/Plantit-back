package com.eugene.diary.auth.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
}
