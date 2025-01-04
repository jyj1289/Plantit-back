package com.eugene.diary.diary.domain;

import com.eugene.diary.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByAuthor(User author);

    boolean existsByAuthorAndCreatedAtBetween(User author, LocalDateTime startTime, LocalDateTime endTime);
}
