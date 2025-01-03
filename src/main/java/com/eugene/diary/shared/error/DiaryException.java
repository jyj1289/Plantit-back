package com.eugene.diary.shared.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiaryException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
