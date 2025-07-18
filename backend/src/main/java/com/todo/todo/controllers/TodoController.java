package com.todo.todo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.todo.todo.models.Todo;
import com.todo.todo.services.TodoService;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    @Autowired
    private TodoService service;
    


    

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping()
    public List<Todo> Get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9")int size){
        System.out.println("Page: "+page+" size: "+size);
        return service.getAllTodos(page,size);
    }
}
