package com.eugene.diary.diary.controller;

import com.eugene.diary.diary.controller.dto.request.DiaryRequest;
import com.eugene.diary.diary.controller.dto.response.DiaryResponse;
import com.eugene.diary.diary.controller.dto.response.ListDiaryResponse;
import com.eugene.diary.diary.service.DiaryService;
import com.eugene.diary.shared.auth.AuthenticationPrincipal;
import com.eugene.diary.shared.response.ListCommonResponse;
import com.eugene.diary.shared.response.SingleCommonResponse;
import com.eugene.diary.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("diary")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SingleCommonResponse<Long> write(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid DiaryRequest diaryRequest
    ) {
        return SingleCommonResponse.ok(
                diaryService.write(user, diaryRequest)
        );
    }

    @GetMapping
    public ListCommonResponse<ListDiaryResponse> getAll(
            @AuthenticationPrincipal User user
    ) {
        return ListCommonResponse.ok(
          diaryService.getAll(user)
        );
    }

    @GetMapping("/{id}")
    public SingleCommonResponse<DiaryResponse> getDiary(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        return SingleCommonResponse.ok(
                diaryService.get(user, id)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateDiary(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody @Valid DiaryRequest request
    ) {
        diaryService.update(user, id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        diaryService.delete(user, id);
    }
}
