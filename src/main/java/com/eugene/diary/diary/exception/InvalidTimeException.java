package com.eugene.diary.diary.exception;

import com.eugene.diary.diary.exception.error.DiaryErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class InvalidTimeException extends DiaryException {
    public InvalidTimeException() {
        super(DiaryErrorProperty.INVALID_TIME);
    }
}
