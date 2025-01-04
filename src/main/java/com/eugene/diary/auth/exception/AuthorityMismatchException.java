package com.eugene.diary.auth.exception;

import com.eugene.diary.auth.exception.error.AuthErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class AuthorityMismatchException extends DiaryException {
    public AuthorityMismatchException() {
        super(AuthErrorProperty.AUTHORITY_MISMATCH);
    }
}
