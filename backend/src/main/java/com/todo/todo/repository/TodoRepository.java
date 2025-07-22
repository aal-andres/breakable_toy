package com.todo.todo.repository;

import java.util.List;

import com.todo.todo.dtos.CreateTodoDto;
import com.todo.todo.dtos.UpdateTodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;

public interface TodoRepository {

    public List<Todo> findAll();
    public Todo create(CreateTodoDto dto);
    public Todo checkTodo(int id);
    public Todo delete(int id);
    public Todo update(int id, UpdateTodoDto dto);
    public List<Todo> searchBy(String name,Status status, Priority priority);
    
} 
