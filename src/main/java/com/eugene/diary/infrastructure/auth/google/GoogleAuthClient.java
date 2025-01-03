package com.eugene.diary.infrastructure.auth.google;

import com.eugene.diary.infrastructure.auth.google.dto.request.GoogleAuthRequest;
import com.eugene.diary.infrastructure.auth.google.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuthClient {

    @PostMapping
    public GoogleTokenResponse getAccessToken(GoogleAuthRequest request);
}
