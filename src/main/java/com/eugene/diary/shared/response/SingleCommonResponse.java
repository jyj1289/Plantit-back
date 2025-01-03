package com.eugene.diary.shared.response;

import lombok.Getter;

@Getter
public class SingleCommonResponse<T> extends CommonResponse {
    T data;

    public SingleCommonResponse(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }
}
