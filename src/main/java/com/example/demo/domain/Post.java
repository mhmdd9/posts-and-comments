package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "POSTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    public Post(Long userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String title;

    @NotNull
    @Column(length = 500)
    private String body;
}
