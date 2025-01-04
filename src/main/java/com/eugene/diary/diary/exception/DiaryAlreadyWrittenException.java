package com.eugene.diary.diary.exception;

import com.eugene.diary.diary.exception.error.DiaryErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class DiaryAlreadyWrittenException extends DiaryException {
    public DiaryAlreadyWrittenException() {
        super(DiaryErrorProperty.DIARY_ALREADY_WRITTEN);
    }
}
