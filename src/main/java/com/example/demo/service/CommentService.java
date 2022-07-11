package com.example.demo.service;

import com.example.demo.dao.CommentRepo;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CommentDto;
import com.example.demo.payload.CommentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    private final CommentRepo repo;
    private final PostService postService;

    public CommentService(CommentRepo repo, @Lazy PostService postService) {
        this.repo = repo;
        this.postService = postService;
    }

    public CommentResponse getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Comment> comments = repo.findAll(pageable);
        List<Comment> commentList = comments.getContent();

        List<CommentDto> content = commentList
                .stream().map(this::mapToDto).collect(Collectors.toList());

        log.info("getting all comments with pagination");

        return new CommentResponse(content, comments.getNumber(), comments.getSize(), comments.getTotalElements());
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setPostId(comment.getPost().getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

        return dto;
    }

    public List<CommentDto> getAllByPostId(Long postId) {
        log.info("getting all comments by a specific post id");

        return repo.findAllByPostId(postId)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Comment save(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setBody(dto.getBody());
        comment.setEmail(dto.getEmail());
        comment.setName(dto.getName());
        Post post = postService.findById(dto.getPostId());
        comment.setPost(post);

        log.info("saving a comment");

        return repo.save(comment);
    }

    public void deleteById(Long id) {
        Comment comment = repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", id));

        log.info("deleting a comment by id");

        repo.delete(comment);
    }
}
