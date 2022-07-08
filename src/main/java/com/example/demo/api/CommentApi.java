package com.example.demo.api;

import com.example.demo.domain.Comment;
import com.example.demo.payload.CommentDto;
import com.example.demo.payload.CommentResponse;
import com.example.demo.service.CommentService;
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
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentApi {

    private final CommentService service;

    @ApiOperation(value = "Get all comments with pagination")
    @GetMapping
    public CommentResponse getAll(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        return service.getAll(pageNo, pageSize);
    }

    @ApiOperation(value = "Get all comments by post id")
    @GetMapping(params = "postId")
    public List<CommentDto> findAllByPostId(@RequestParam Long postId) {
        return service.getAllByPostId(postId);
    }

    @ApiOperation(value = "Save a new comment")
    @PostMapping
    public ResponseEntity<Comment> save(@RequestBody CommentDto entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a comment by id")
    @PatchMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody CommentDto entity) {
        entity.setId(id);
        return new ResponseEntity<>(service.save(entity), HttpStatus.OK);
    }

    @ApiOperation("Delete a comment by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Comment is deleted Successfully.", HttpStatus.OK);
    }
}
