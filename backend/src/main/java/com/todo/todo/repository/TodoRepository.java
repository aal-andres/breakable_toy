package com.todo.todo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;

public interface TodoRepository {

    public List<Todo> findAll();
    public Todo create(TodoDto dto);
    public Todo markDone(int id);
    public Todo markUndone(int id);
    public boolean delete(int id);
    public Todo update(int id, TodoDto dto);
    public Stream<Todo> searchBy(String name,String status, String priority);
    public List<Todo> filterByDueDatePriority(Priority priority, LocalDateTime due_date);
    public Todo getById(int id);
    
} 
