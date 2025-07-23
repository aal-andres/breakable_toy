package com.todo.todo.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;

@Repository()
public class TodoRepositoryImpl implements TodoRepository{

    private final TodoRepository repository;

    TodoRepositoryImpl(@Qualifier("TodoRepositoryMemory") TodoRepository repository){
        this.repository = repository;
    }


    
    public List<Todo> findAll(){
        return repository.findAll();
    }

    public List<Todo> searchBy(String name,Status status,Priority priority){
        return repository.searchBy(name, status, priority);
    }

    public Todo create(TodoDto dto){
        return repository.create(dto);
        
    }

    public List<Todo> filterByDueDatePriority(Priority priority, LocalDateTime due_date){

        return repository.filterByDueDatePriority(priority, due_date);
    }

    public Todo delete(int id){
        return repository.delete(id);
    }

    public Todo update(int id, TodoDto dto){
        return update(id, dto);
    }

    public Todo checkTodo(int id){
        return repository.checkTodo(id);
    }
}
