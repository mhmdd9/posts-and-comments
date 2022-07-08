package com.example.demo.dao;

import com.example.demo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserIdAndCompleted(Long userId, Boolean completed);
}
