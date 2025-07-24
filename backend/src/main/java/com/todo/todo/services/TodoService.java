package com.todo.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;
import com.todo.todo.repository.TodoRepositoryImpl;


@Service
public class TodoService {

    @Autowired
    private TodoRepositoryImpl repository;


    public Todo getById(int id){
        return repository.getById(id);
    }
    

    public List<Todo> getAllTodos(int page, int size){
        List<Todo> allTodos = this.repository.findAll();
        int from = page*size;
        int to = Math.min(from+size, allTodos.size());
        return allTodos.subList(from, to);
    }

    public List<Todo> searchBy(String name,Status status, Priority priority){
        return repository.searchBy(name,status, priority);
    }

    public Todo createTodo(TodoDto dto){
        return this.repository.create(dto);
    }

    public boolean delete(int id ){
        return repository.delete(id);
    }

    public Todo update(int id, TodoDto dto){
        return repository.update(id, dto);
    }

    public Todo markDone(int id){
        return repository.markDone(id);
    }

    public Todo markUndone(int id){
        return repository.markUndone(id);
    }
}
