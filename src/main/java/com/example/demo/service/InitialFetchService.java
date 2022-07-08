package com.example.demo.service;

import com.example.demo.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

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
/*        ResponseEntity<Posts[]> postsEntity = restTemplate.getForEntity(baseUrl + "/posts", Posts[].class);
        ResponseEntity<CommentsDto[]> commentsEntity = restTemplate.getForEntity(baseUrl + "/comments", CommentsDto[].class);
        ResponseEntity<Todos[]> todosEntity = restTemplate.getForEntity(baseUrl + "/todos", Todos[].class);

        if(postsEntity.getBody() == null || commentsEntity.getBody() == null || todosEntity.getBody() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        throw new CustomException(HttpStatus.BAD_REQUEST, "Fetching data failed!");

        Arrays.stream(postsEntity.getBody()).forEach(postsService::save);
        Arrays.stream(commentsEntity.getBody()).forEach(commentsService::save);
        Arrays.stream(todosEntity.getBody()).forEach(todosService::save);*/
    }
}
