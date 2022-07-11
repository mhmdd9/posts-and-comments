package com.example.demo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    public CommentDto(Long postId, String name, String email, String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
