package com.eugene.diary.user.exception.error;

import com.eugene.diary.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorProperty implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 없습니다.");

    private final HttpStatus status;
    private final String message;
}
