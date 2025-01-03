package com.eugene.diary.user.exception;

import com.eugene.diary.shared.error.DiaryException;
import com.eugene.diary.user.exception.error.UserErrorProperty;

public class UserNotFoundException extends DiaryException {
    public UserNotFoundException() {
        super(UserErrorProperty.USER_NOT_FOUND);
    }
}
