package com.eugene.diary.diary.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

    @NotBlank(message = "필수값입니다.")
    @Size(max = 150, message = "150자 이하여야합니다.")
    private String title;

    @NotBlank(message = "필수값입니다.")
    @Size(max = 5000, message = "5000자 이하여야합니다.")
    private String content;
}
