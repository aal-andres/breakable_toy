package com.todo.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo.dtos.CreateTodoDto;
import com.todo.todo.dtos.UpdateTodoDto;
import com.todo.todo.models.Todo;
import com.todo.todo.repository.TodoRepository;


@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;
    

    public List<Todo> getAllTodos(int page, int size){
        List<Todo> allTodos = this.repository.getAllTodos();
        int from = page*size;
        int to = Math.min(from+size, allTodos.size());
        System.out.println("from : "+from+" to: "+to);
        return allTodos.subList(from, to);
    }

    public Todo createTodo(CreateTodoDto dto){
        return this.repository.createTodo(dto);
    }

    public Todo delete(int id ){
        return repository.delete(id);
    }

    public Todo update(int id, UpdateTodoDto dto){
        return repository.update(id, dto);
    }
}
