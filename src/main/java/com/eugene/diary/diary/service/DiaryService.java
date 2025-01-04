package com.eugene.diary.diary.service;

import com.eugene.diary.auth.exception.AuthorityMismatchException;
import com.eugene.diary.diary.controller.dto.request.DiaryRequest;
import com.eugene.diary.diary.controller.dto.response.DiaryResponse;
import com.eugene.diary.diary.controller.dto.response.ListDiaryResponse;
import com.eugene.diary.diary.domain.Diary;
import com.eugene.diary.diary.domain.DiaryRepository;
import com.eugene.diary.diary.exception.DiaryAlreadyWrittenException;
import com.eugene.diary.diary.exception.DiaryNotFoundException;
import com.eugene.diary.diary.exception.InvalidTimeException;
import com.eugene.diary.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Long write(User author, DiaryRequest request) {
        Diary diary = new Diary(request.getContent(), author);

        validateWrite(author);

        return diaryRepository.save(diary).getId();
    }

    @Transactional(readOnly = true)
    public List<ListDiaryResponse> getAll(User author) {
        List<Diary> diaryList = diaryRepository.findAllByAuthor(author);


        return diaryList.stream()
                .map(ListDiaryResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public DiaryResponse get(User author, Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
        validateAuthor(author, diary);

        return new DiaryResponse(diary);
    }

    @Transactional
    public void update(User author, Long id, DiaryRequest request) {
        Diary diary = diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
        validateAuthor(author, diary);
        validateTime(diary);

        diary.update(request.getContent());
    }

    @Transactional
    public void delete(User author, Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(DiaryNotFoundException::new);
        validateAuthor(author, diary);

        diaryRepository.delete(diary);
    }

    private void validateWrite(User author) {
        LocalDateTime startOfToday = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfToday = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        if (diaryRepository.existsByAuthorAndCreatedAtBetween(author, startOfToday, endOfToday)) {
            throw new DiaryAlreadyWrittenException();
        }
    }

    private void validateAuthor(User author, Diary diary) {
        if (!diary.isAuthor(author)) {
            throw new AuthorityMismatchException();
        }
    }

    private void validateTime(Diary diary) {
        LocalDateTime allowedUntil = diary.getUpdatedAt().plusDays(1).toLocalDate().atStartOfDay();
        if (LocalDateTime.now().isAfter(allowedUntil)) {
            throw new InvalidTimeException();
        }
    }
}
