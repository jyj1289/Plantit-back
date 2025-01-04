package com.eugene.diary.user.controller.dto.response;

import com.eugene.diary.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;
    private final String name;
    private final Integer percent;
    private final Integer level;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.percent = (int) (((user.getCount() % 5) / 5.0) * 100); // 남은 진행률
        this.level = user.getLevel();
    }
}
