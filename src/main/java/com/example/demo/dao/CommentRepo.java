package com.example.demo.dao;

import com.example.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("SELECT C FROM COMMENTS C WHERE C.post.id = :postId")
    List<Comment> findAllByPostId(Long postId);
}
