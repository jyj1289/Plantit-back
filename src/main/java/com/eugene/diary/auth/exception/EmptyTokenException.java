package com.eugene.diary.auth.exception;

import com.eugene.diary.auth.exception.error.AuthErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class EmptyTokenException extends DiaryException {
    public EmptyTokenException() {
        super(AuthErrorProperty.EMPTY_TOKEN);
    }
}
