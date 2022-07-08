package com.example.demo.api;

import com.example.demo.domain.Post;
import com.example.demo.payload.CommentDto;
import com.example.demo.payload.PostResponse;
import com.example.demo.service.PostService;
import com.example.demo.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Restful APIs for CRUD Operations on Posts")
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostApi {

    private final PostService service;

    @ApiOperation(value = "Get all posts with pagination")
    @GetMapping
    public PostResponse getAll
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        return service.getAll(pageNo, pageSize);
    }

    @ApiOperation(value = "Get post by id")
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @ApiOperation(value = "Get comments of specific post by post id")
    @GetMapping("/{id}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable Long id) {
        return service.getAllCommentsByPostId(id);
    }

    @ApiOperation(value = "Get all posts with a specific keyword in their title")
    @GetMapping(params = "title")
    public List<Post> findAllByTitle(@RequestParam String title) {
        return service.findAllByTitleLike(title);
    }

    @ApiOperation(value = "Save a new post")
    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        return new ResponseEntity<>(service.save(post), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a post by id")
    @PatchMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return new ResponseEntity<>(service.save(post), HttpStatus.OK);
    }

    @ApiOperation("Delete a post by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Post is deleted Successfully.", HttpStatus.OK);
    }
}
