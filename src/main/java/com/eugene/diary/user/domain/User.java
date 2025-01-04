package com.eugene.diary.user.domain;

import com.eugene.diary.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer level;

    public void count() {
        this.count += 1;
        if(this.count % 5 == 0) {
            this.level += 1;
        }
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.count = 0;
        this.level = 1;
    }
}
