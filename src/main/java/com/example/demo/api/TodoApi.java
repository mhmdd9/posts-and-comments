package com.example.demo.api;

import com.example.demo.domain.Todo;
import com.example.demo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Restful APIs for getting Todos")
@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class TodoApi {

    private final TodoService service;

    @ApiOperation(value = "Get all todos")
    @GetMapping
    public List<Todo> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get all todos by user id and completed flag")
    @GetMapping(params = {"userId", "completed"})
    public List<Todo> findAllByUserIdAndCompleted
            (@RequestParam Long userId, @RequestParam Boolean completed) {
        return service.findAll(userId, completed);
    }
}
