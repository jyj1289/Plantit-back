package com.eugene.diary.diary.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_diary")
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT", length = 5000)
    private String content;

    @Column(nullable = false)
    private LocalDate writtenAt;

    public Diary(String content, LocalDate writtenAt) {
        this.content = content;
        this.writtenAt = writtenAt;
    }
}
