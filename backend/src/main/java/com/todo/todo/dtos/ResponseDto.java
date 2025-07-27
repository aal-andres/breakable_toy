package com.todo.todo.dtos;

import java.util.List;

import com.todo.todo.models.Todo;

public class ResponseDto {
    public List<Todo> todos;
    public TimeStatisticsDto timeMetrics;
    public boolean has_next_page;
}
