package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "TODOS")
@Data
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String title;

    private Boolean completed;
}
