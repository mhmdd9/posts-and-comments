package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.domain.Todo;
import com.example.demo.exception.CustomException;
import com.example.demo.payload.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class InitialFetchService {

    private final RestTemplate restTemplate;
    private final PostService postService;
    private final CommentService commentService;
    private final TodoService todoService;

    @PostConstruct
    public void fetchAndStoreData() {

        String baseUrl = "https://jsonplaceholder.typicode.com";
        ResponseEntity<Post[]> postsEntity = restTemplate.getForEntity(baseUrl + "/posts", Post[].class);
        ResponseEntity<CommentDto[]> commentsEntity = restTemplate.getForEntity(baseUrl + "/comments", CommentDto[].class);
        ResponseEntity<Todo[]> todosEntity = restTemplate.getForEntity(baseUrl + "/todos", Todo[].class);

        if(postsEntity.getBody() == null || commentsEntity.getBody() == null || todosEntity.getBody() == null)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            throw new CustomException(HttpStatus.BAD_REQUEST, "Fetching data failed!");

        Arrays.stream(postsEntity.getBody()).forEach(postService::save);
        Arrays.stream(commentsEntity.getBody()).forEach(commentService::save);
        Arrays.stream(todosEntity.getBody()).forEach(todoService::save);
    }
}
