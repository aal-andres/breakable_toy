package com.todo.todo.models;

import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import java.time.LocalDateTime;


public class Todo {
    private int id;
    private String name;
    private Status status;
    public LocalDateTime created_at;
    private LocalDateTime due_date;
    private LocalDateTime completed_at;
    private Priority priority;

    public Todo(int id, String name,String priority,LocalDateTime due_date ){
        this.id = id;
        this.name = name;
        this.created_at = LocalDateTime.now().withSecond(0).withNano(0);
        this.priority = Priority.valueOf(priority.toUpperCase());
        this.due_date = due_date;
        this.status = Status.UNDONE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public LocalDateTime getDueDate() {
        return due_date;
    }

    public LocalDateTime getCompletedAt() {
        return completed_at;
    }

    public Priority getPriority() {
        return priority;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueDate(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public void setCompletedAt(LocalDateTime completed_at) {
        this.completed_at = completed_at;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}


