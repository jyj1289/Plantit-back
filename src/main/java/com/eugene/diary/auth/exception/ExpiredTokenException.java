package com.eugene.diary.auth.exception;

import com.eugene.diary.auth.exception.error.AuthErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class ExpiredTokenException extends DiaryException {
    public ExpiredTokenException() {
        super(AuthErrorProperty.EXPIRED_TOKEN);
    }
}
