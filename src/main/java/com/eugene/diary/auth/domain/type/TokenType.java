package com.eugene.diary.auth.domain.type;

import com.eugene.diary.shared.config.properties.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType implements EnumProperty {

    REFRESH_TOKEN("refresh token"),
    ACCESS_TOKEN("access token");

    private final String description;
}
