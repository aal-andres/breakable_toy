package com.todo.todo.dtos;

import com.todo.todo.models.Todo;

public class DoneUndoneResponseDto{
    private Todo todo;
    private TimeStatisticsDto timeStatistics;

    public void setTodo(Todo todo){
        this.todo = todo;
    }


    public void setTimeStatistics(TimeStatisticsDto time){
        this.timeStatistics = time;
    }

    public Todo getTodo(){
        return this.todo;
    }

    public TimeStatisticsDto getTimeStatistics(){
        return this.timeStatistics;
    }
}