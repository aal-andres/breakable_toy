package com.todo.todo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;


@Repository("TodoRepositoryMemory")
public class TodoRepositoryMemory implements TodoRepository{
     private List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(1, "Buy groceries", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(2, "Finish project", "Low",LocalDateTime.parse("2025-08-20T11:30:00")),
            new Todo(3, "i don't know", "medium",LocalDateTime.parse("2025-08-20T12:30:00")),
            new Todo(4, "buy chips", "High",LocalDateTime.parse("2025-09-20T11:30:00")),
            new Todo(5, "pet the cat", "High",LocalDateTime.parse("2025-09-20T11:30:00")),
            new Todo(6, "feed the cat", "High",LocalDateTime.parse("2025-09-20T11:30:00")),
            new Todo(7, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(8, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(9, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(10, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(11, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(12, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(13, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(14, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(15, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(16, "Buy tomatoes", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(17, "buy something i guesssssss", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(18, "buy type shi", "Low",LocalDateTime.parse("2025-07-20T11:30:00"))

    ));
    


    
    public List<Todo> findAll(){
        return todos;
    }

    public Stream<Todo> searchBy(String name,String status,String priority){
        Stream<Todo> filteredList = todos.stream();
        if(name!=null && !name.isEmpty()){
            System.out.println("entreeeeee: "+name);
            filteredList = filteredList.filter(t -> t.name.toLowerCase().contains(name.toLowerCase()));
        }

        if(status !=null ){
        if(!status.equals("ALL")){
            
                filteredList = filteredList.filter(t -> t.status == Status.valueOf(status.toUpperCase()));
            }
        }

        if( priority != null){
            
        if(!priority.equals("ALL")){

                filteredList = filteredList.filter(t -> t.priority == Priority.valueOf(priority.toUpperCase()));
            }

        }

        return filteredList;
    }

    public Todo create(TodoDto dto){
        
        Todo todo = new Todo(todos.get(todos.size() - 1).id + 1, dto.name, dto.priority, dto.due_date);
        todos.add(todo);
        return todo;
    }

    public Todo getById(int id){
        Todo todo = todos.get(id-1);
        return todo;
    }

    public List<Todo> filterByDueDatePriority(Priority priority, LocalDateTime due_date){
        return todos.stream().filter(todo -> todo.priority == priority).collect(Collectors.toList());
    }

    public boolean delete(int id){
         if(todos.remove(id - 1) != null){
            return true;
         }else{
            return false;
         }

        
    }

    public Todo update(int id, TodoDto dto){
        Todo todo = todos.get(id -1);
        if(dto.name !=null){
            todo.name = dto.name;
        }
        if (dto.priority!=null) {
            todo.priority = Priority.valueOf(dto.priority.toUpperCase());
        }
        if(dto.due_date!=null){
            todo.due_date = dto.due_date;
        }
        return todo;
    }

    public Todo markDone(int id){
        Todo todo = todos.get(id -1);
        if(todo.status == Status.DONE){
            return todo;
        }else{

            todo.status = Status.DONE;
            todo.completed_at = LocalDateTime.now().withNano(0);
            return todo;
        }
    }

    public Todo markUndone(int id){
        Todo todo = todos.get(id -1);

        if(todo.status == Status.UNDONE){
            return todo;
        }else{
            todo.status = Status.UNDONE;
            todo.completed_at = null;
            return todo;
        }
    }
}
