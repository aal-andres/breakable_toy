package com.todo.todo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
            new Todo(2, "Finish project", "Low",LocalDateTime.parse("2025-08-16T11:30:00")),
            new Todo(3, "lift weights", "medium",LocalDateTime.parse("2025-09-20T12:30:00")),
            new Todo(4, "buy chips", "High",LocalDateTime.parse("2025-09-20T11:30:00")),
            new Todo(5, "pet the cat", "High",LocalDateTime.parse("2025-09-20T16:30:00")),
            new Todo(6, "feed the cat", "High",LocalDateTime.parse("2025-09-20T11:30:00")),
            new Todo(7, "finish university", "High",LocalDateTime.parse("2025-07-02T12:30:00")),
            new Todo(9, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-08-20T11:30:00")),
            new Todo(10, "i don't know anymore aaaaaa", "Medium",LocalDateTime.parse("2025-08-20T11:30:00")),
            new Todo(11, "i don't know anymore aaaaaaaaaah", "High",LocalDateTime.parse("2025-08-20T11:30:00")),
            new Todo(12, "Buy tomatoes", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(13, "buy something i guesssssss", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(14, "run 5 km", "Low",LocalDateTime.parse("2025-07-20T11:30:00"))

    ));
    


    
    public List<Todo> findAll(){
        return todos;
    }

    public Stream<Todo> searchBy(String name,String status,String priority){
        Stream<Todo> filteredList = todos.stream();
        if(name!=null && !name.isEmpty()){
            System.out.println("entreeeeee: "+name);
            filteredList = filteredList.filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()));
        }

        if(status !=null ){
        if(!status.equals("ALL")){
            
                filteredList = filteredList.filter(t -> t.getStatus() == Status.valueOf(status.toUpperCase()));
            }
        }

        if( priority != null){
            
        if(!priority.equals("ALL")){

                filteredList = filteredList.filter(t -> t.getPriority() == Priority.valueOf(priority.toUpperCase()));
            }

        }

        return filteredList;
    }

    public Todo create(TodoDto dto){
        
        Todo todo = new Todo(todos.get(todos.size() - 1).getId() + 1, dto.name, dto.priority, dto.due_date);
        todos.add(todo);
        return todo;
    }

    public Todo getById(int id){
        Todo todo = todos.get(id-1);
        return todo;
    }

    public List<Todo> filterByDueDatePriority(Priority priority, LocalDateTime due_date){
        return todos.stream().filter(todo -> todo.getPriority() == priority).collect(Collectors.toList());
    }

    public boolean delete(int id){
       //  if(todos.remove(id - 1) != null){
       //     return true;
       //  }else{
       //     return false;
       //  }

       Iterator<Todo> iterator = todos.iterator();
       while(iterator.hasNext()){
        Todo todo = iterator.next();

            if(todo.getId() == id){
                iterator.remove();
                return true;
            }
       }
       return false;
        
    }

    public Todo update(int id, TodoDto dto){
        Iterator<Todo> iterator = todos.iterator();
        Todo todo = null;
        while(iterator.hasNext()){
            todo = iterator.next();

            if(todo.getId() == id){

                if(dto.name !=null){
                    todo.setName(dto.name);
                }
                if (dto.priority!=null) {
                    todo.setPriority(Priority.valueOf(dto.priority.toUpperCase()));
                }
                if(dto.due_date!=null){
                    todo.setDueDate(dto.due_date);
                }
                break;
            }
        }
        return todo;
    }

    public Todo markDone(int id){
        Iterator<Todo> iterator = todos.iterator();
        Todo todo = null;
        while(iterator.hasNext()){
            todo = iterator.next();
            if(todo.getId() == id){

                if(todo.getStatus() == Status.DONE){
                    return todo;
                }else{
        
                    todo.setStatus(Status.DONE);
                    todo.setCompletedAt(LocalDateTime.now().withNano(0));
                    return todo;
                }
            }
        }
        return todo;
    }

    public Todo markUndone(int id){
         Iterator<Todo> iterator = todos.iterator();
        Todo todo = null;
        while(iterator.hasNext()){
            todo = iterator.next();
            if(todo.getId() == id){

                if(todo.getStatus() == Status.UNDONE){
                    return todo;
                }else{
                    todo.setStatus(Status.UNDONE);
                    todo.setCompletedAt(null);
                    return todo;
                }
            }
        }
        return todo;
    }
}
