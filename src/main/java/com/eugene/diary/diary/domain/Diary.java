package com.eugene.diary.diary.domain;

import com.eugene.diary.shared.entity.BaseTimeEntity;
import com.eugene.diary.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_diary")
@Entity
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT", length = 5000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public boolean isAuthor(User user) {
        return author.equals(user);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Diary(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
