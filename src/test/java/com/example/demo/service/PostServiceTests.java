package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.dao.PostRepo;
import com.example.demo.domain.Post;
import com.example.demo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PostServiceTests {

    @Mock
    private PostRepo postRepo;

    @InjectMocks
    private PostService postService;

    private Post post;

    @BeforeEach
    public void setup() {
        post = Post.builder()
                .id(101L)
                .body("test body for post")
                .title("test title for post")
                .userId(101L)
                .build();
    }

    @DisplayName("Junit test for saving post method")
    @Test
    public void testSavePost() {
        given(postRepo.findById(post.getId()))
                .willReturn(Optional.empty());

        given(postRepo.save(post)).willReturn(post);

        System.out.println(postRepo);
        System.out.println(postService);

        Post savedPost = postService.save(post);

        System.out.println(savedPost);

        assertThat(savedPost).isNotNull();
    }

    @DisplayName("JUnit test for saving Post method which throws exception")
    @Test
    public void givenExistingEmail_whenSavePost_thenThrowsException(){
        // given - precondition or setup
        given(postRepo.findById(post.getId()))
                .willReturn(Optional.of(post));

        System.out.println(postRepo);
        System.out.println(postService);

        // when -  action or the behaviour that we are going test
        assertThrows(ResourceNotFoundException.class, () -> {
            postService.save(post);
        });

        // then
        verify(postRepo, never()).save(any(Post.class));
    }

    // JUnit test for getAllPosts method
    @DisplayName("JUnit test for getAllPosts method")
    @Test
    public void givenPostsList_whenGetAllPosts_thenReturnPostsList(){
        // given - precondition or setup

        Post post1 = Post.builder()
                .id(102L)
                .body("Body 2")
                .title("Title 2")
                .userId(102L)
                .build();

        given(postRepo.findAll()).willReturn(List.of(post, post1));

        // when -  action or the behaviour that we are going test
        List<Post> postsList = postService.getAll();

        // then - verify the output
        assertThat(postsList).isNotNull();
        assertThat(postsList.size()).isEqualTo(2);
    }

    // JUnit test for getAllPosts method
    @DisplayName("JUnit test for getAllPosts method (negative scenario)")
    @Test
    public void givenEmptyPostsList_whenGetAllPosts_thenReturnEmptyPostsList(){
        // given - precondition or setup

        Post post1 = Post.builder()
                .id(103L)
                .body("Body 3")
                .title("Title 3")
                .userId(103L)
                .build();

        given(postRepo.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Post> postsList = postService.getAll();

        // then - verify the output
        assertThat(postsList).isEmpty();
        assertThat(postsList.size()).isEqualTo(0);
    }

    // JUnit test for findPostById method
    @DisplayName("JUnit test for getPostById method")
    @Test
    public void givenPostId_whenGetPostById_thenReturnPostObject(){
        // given
        given(postRepo.findById(1L)).willReturn(Optional.of(post));

        // when
        Post savedPost = postService.findById(post.getId());

        // then
        assertThat(savedPost).isNotNull();

    }

    // JUnit test for deletePost method
    @DisplayName("JUnit test for deletePost method")
    @Test
    public void givenPostId_whenDeletePost_thenNothing(){
        // given - precondition or setup
        long postId = 1L;

        willDoNothing().given(postRepo).deleteById(postId);

        // when -  action or the behaviour that we are going test
        postService.deleteById(postId);

        // then - verify the output
        verify(postRepo, times(1)).deleteById(postId);
    }
}
