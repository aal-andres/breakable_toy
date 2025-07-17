package com.todo.todo.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Todo {
    public int id;
    public String name;
    public boolean done;
   // public String text;
    public LocalDateTime created_at;
    //public String due_date;
    public LocalDateTime completed_at;
    public String priority;

    public Todo(int id, String name,String priority ){
        this.id = id;
        this.name = name;
        this.created_at = LocalDateTime.now().withNano(0);
        this.priority = priority;
    }

    
}


