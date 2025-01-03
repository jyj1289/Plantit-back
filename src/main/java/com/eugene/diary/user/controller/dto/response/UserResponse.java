package com.eugene.diary.user.controller.dto.response;

import com.eugene.diary.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;
    private final String name;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
