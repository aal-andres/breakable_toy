package com.todo.todo.models;

import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import java.time.LocalDateTime;

public class Todo {
    public int id;
    public String name;
    public Status status;
    public LocalDateTime created_at;
    public LocalDateTime due_date;
    public LocalDateTime completed_at;
    public Priority priority;

    public Todo(int id, String name,String priority ){
        this.id = id;
        this.name = name;
        this.created_at = LocalDateTime.now().withSecond(0).withNano(0);
        this.priority = Priority.valueOf(priority.toUpperCase());
        this.status = Status.UNDONE;
    }

    
}


