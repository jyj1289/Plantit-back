package com.eugene.diary.user.controller;

import com.eugene.diary.shared.auth.AuthenticationPrincipal;
import com.eugene.diary.shared.response.CommonResponse;
import com.eugene.diary.shared.response.SingleCommonResponse;
import com.eugene.diary.user.controller.dto.response.UserResponse;
import com.eugene.diary.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping
    public SingleCommonResponse<UserResponse> getUserInfo(@AuthenticationPrincipal User user) {
        return CommonResponse.success(
                new UserResponse(user)
        );
    }
}
