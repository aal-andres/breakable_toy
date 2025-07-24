package com.todo.todo.controllers;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;
import com.todo.todo.services.TodoService;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    @Autowired
    private TodoService service;
    


    

    @GetMapping()
    public List<Todo> Get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9")int size
    ,@RequestParam(required = false) String name, @RequestParam(required = false) Status status
    , @RequestParam(required = false) Priority priority){
        return service.getAllTodos(page,size,name,status,priority);
    }

    @GetMapping("/search")
    public Stream<Todo> searchBy(@RequestParam String name, @RequestParam Status status, @RequestParam Priority priority){
        return service.searchBy(name, status, priority);
    }

    @PostMapping()
    public Todo Post(@RequestBody TodoDto dto){
        return service.createTodo(dto);
    }

    @DeleteMapping("/{id}")
    public Todo Delete(@PathVariable() int id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable() int id,@RequestBody TodoDto dto){
        return service.update(id,dto);
    }

    @PutMapping("/{id}/undone")
    public Todo checkTodo(@PathVariable() int id){
        return service.markUndone(id);
    }

    @PostMapping("/{id}/done")
    public Todo markDone(@PathVariable() int id){
        return service.markDone(id);
    }

}
