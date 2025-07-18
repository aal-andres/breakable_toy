package com.todo.todo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.todo.todo.models.Todo;

@Repository
public class TodoRepository {

    private List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(1, "Buy groceries", "High"),
            new Todo(2, "Finish project", "Low"),
            new Todo(3, "i don't know", "medium"),
            new Todo(4, "buy chips", "High"),
            new Todo(5, "pet the cat", "High"),
            new Todo(6, "feed the cat", "High"),
            new Todo(8, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(9, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(10, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(11, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(12, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(13, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(14, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(15, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(16, "i don't know anymore aaaaaaaaaah", "High")

    ));
    
    public List<Todo> getAllTodos(){
        return todos;
    }
}
