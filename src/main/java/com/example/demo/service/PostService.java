package com.example.demo.service;

import com.example.demo.dao.PostRepo;
import com.example.demo.domain.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CommentDto;
import com.example.demo.payload.PostResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo repo;
    private final CommentService commentService;

    public PostResponse getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = repo.findAll(pageable);
        List<Post> postList = posts.getContent();

        return new PostResponse(postList, posts.getNumber(), posts.getSize(), posts.getTotalElements());
    }

    public List<Post> findAllByTitleLike(String keyword) {
        return repo.findAllByTitleLike(keyword);
    }

    public Post save(Post post) {
        return repo.save(post);
    }

    public void deleteById(Long id) {
        Post post = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        repo.delete(post);
    }

    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        return commentService.getAllByPostId(postId);
    }

    public Post findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }
}
