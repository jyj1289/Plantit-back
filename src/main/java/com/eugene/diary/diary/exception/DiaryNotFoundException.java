package com.eugene.diary.diary.exception;

import com.eugene.diary.diary.exception.error.DiaryErrorProperty;
import com.eugene.diary.shared.error.DiaryException;

public class DiaryNotFoundException extends DiaryException {
    public DiaryNotFoundException() {
        super(DiaryErrorProperty.DIARY_NOT_FOUND);
    }
}
