package com.eugene.diary.user.service;

import com.eugene.diary.user.domain.User;
import com.eugene.diary.user.domain.UserRepository;
import com.eugene.diary.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
