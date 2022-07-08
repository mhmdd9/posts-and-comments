package com.example.demo.payload;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
