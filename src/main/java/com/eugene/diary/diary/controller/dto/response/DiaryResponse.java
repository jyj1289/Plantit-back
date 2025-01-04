package com.eugene.diary.diary.controller.dto.response;

import com.eugene.diary.diary.domain.Diary;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DiaryResponse {

    private final Long id;

    private final String content;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public DiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.content = diary.getContent();
        this.createdAt = diary.getCreatedAt();
        this.updatedAt = diary.getUpdatedAt();
    }
}
