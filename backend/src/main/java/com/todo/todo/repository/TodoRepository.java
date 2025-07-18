package com.todo.todo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.todo.todo.dtos.CreateTodoDto;
import com.todo.todo.dtos.UpdateTodoDto;
import com.todo.todo.enums.Priority;
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
            new Todo(7, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(8, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(9, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(10, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(11, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(12, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(13, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(14, "i don't know anymore aaaaaaaaaah", "High"),
            new Todo(15, "i don't know anymore aaaaaaaaaah", "High")

    ));


    
    public List<Todo> getAllTodos(){
        return todos;
    }

    public Todo createTodo(CreateTodoDto dto){
        
        Todo todo = new Todo(todos.get(todos.size() - 1).id + 1, dto.name, dto.priority);
        todos.add(todo);
        return todo;
    }

    public Todo delete(int id){
        return todos.remove(id - 1);
        
    }

    public Todo update(int id, UpdateTodoDto dto){
        Todo todo = todos.get(id -1);
        if(dto.name !=null){
            todo.name = dto.name;
        }
        if (dto.priority!=null) {
            todo.priority = Priority.valueOf(dto.priority.toUpperCase());
        }
        return todo;
    }
}
