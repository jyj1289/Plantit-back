package com.eugene.diary.diary.controller.dto.response;

import com.eugene.diary.diary.domain.Diary;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ListDiaryResponse {

    private final Long id;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public ListDiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.createdAt = diary.getCreatedAt();
        this.updatedAt = diary.getUpdatedAt();
    }
}
