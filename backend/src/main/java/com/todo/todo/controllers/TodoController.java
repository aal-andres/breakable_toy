package com.todo.todo.controllers;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo.dtos.DoneUndoneResponseDto;
import com.todo.todo.dtos.ResponseDto;
import com.todo.todo.dtos.TimeStatisticsDto;
import com.todo.todo.dtos.TodoDto;
import com.todo.todo.models.Todo;
import com.todo.todo.services.TodoService;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:8080")
public class TodoController {

    @Autowired
    private TodoService service;
    


    

    @GetMapping()
    public ResponseDto Get(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int size
    ,@RequestParam(required = false) String name, @RequestParam(required = false) String status
    , @RequestParam(required = false) String priority){
        return service.getAllTodos(page -1,size,name,status,priority);
    }

   

    @PostMapping()
    public Todo Post(@RequestBody TodoDto dto){
        return service.createTodo(dto);
    }

    @DeleteMapping("/{id}")
    public boolean Delete(@PathVariable() int id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable() int id,@RequestBody TodoDto dto){
        return service.update(id,dto);
    }

    @PutMapping("/{id}/undone")
    public DoneUndoneResponseDto checkTodo(@PathVariable() int id){
        return service.markUndone(id);
    }

    @PostMapping("/{id}/done")
    public DoneUndoneResponseDto markDone(@PathVariable() int id){
        return service.markDone(id);
    }

}
