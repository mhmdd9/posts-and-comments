package com.example.demo.service;

import com.example.demo.dao.TodoRepo;
import com.example.demo.domain.Todo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepo repo;

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public List<Todo> findAll(Long userId, Boolean completed) {
        return repo.findAllByUserIdAndCompleted(userId, completed);
    }

    public Todo save(Todo todo) {
        return repo.save(todo);
    }
}
