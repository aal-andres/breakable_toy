package com.todo.todo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo.models.Todo;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {
    private List<Todo> todos;
    


    public void GenerateTodos(){
        todos = new ArrayList<Todo>();

            todos.add(new Todo(1, "Buy groceries", "High"));
            todos.add(new Todo(2, "Finish project", "Low"));
            todos.add(new Todo(3, "i don't know", "medium"));
            todos.add(new Todo(4, "buy chips", "High"));
            todos.add(new Todo(5, "pet the cat", "High"));
            todos.add(new Todo(6, "feed the cat", "High"));
            todos.add(new Todo(7, "i don't know anymore aaaaaaaaaah", "High"));
        
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping()
    public List<Todo> Get(){
        GenerateTodos();
        return todos;
    }
}
