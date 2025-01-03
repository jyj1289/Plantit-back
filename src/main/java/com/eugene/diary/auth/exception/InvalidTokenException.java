package com.eugene.diary.auth.exception;

import com.eugene.diary.auth.exception.error.AuthErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class InvalidTokenException extends DiaryException {
    public InvalidTokenException() {
        super(AuthErrorProperty.INVALID_TOKEN);
    }
}
