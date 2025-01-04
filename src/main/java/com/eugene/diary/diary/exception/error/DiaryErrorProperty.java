package com.eugene.diary.diary.exception.error;

import com.eugene.diary.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DiaryErrorProperty implements ErrorProperty {
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 일기를 찾을 수 없습니다."),
    DIARY_ALREADY_WRITTEN(HttpStatus.CONFLICT, "일기는 하루에 한 번만 작성할 수 있습니다."),
    INVALID_TIME(HttpStatus.CONFLICT, "당일 일기만 수정할 수 있습니다.");

    private final HttpStatus status;
    private final String message;
}
