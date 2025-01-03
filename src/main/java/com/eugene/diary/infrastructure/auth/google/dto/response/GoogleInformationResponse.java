package com.eugene.diary.infrastructure.auth.google.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleInformationResponse {

    private String email;
    private String name;
}
