package com.todo.todo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo.dtos.DoneUndoneResponseDto;
import com.todo.todo.dtos.ResponseDto;
import com.todo.todo.dtos.TimeStatisticsDto;
import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;
import com.todo.todo.repository.TodoRepositoryImpl;


@Service
public class TodoService {

    @Autowired
    private TodoRepositoryImpl repository;


    public Todo getById(int id){
        return repository.getById(id);
    }
    

    public ResponseDto getAllTodos(int page, int size, String name,String status, String priority){
        Stream<Todo> allTodos = this.repository.searchBy(name,status,priority);
       List<Todo> paginatedTodos =  allTodos.skip((long)page*size).limit(size).collect(Collectors.toList());

       boolean has_another_page = this.hasAnotherPage(page, size, paginatedTodos);

       ResponseDto response = new ResponseDto();
       response.has_next_page = has_another_page;
       response.todos = paginatedTodos;
       response.timeMetrics = this.getTimeStatistics();
       
       return response;
    }

    public boolean hasAnotherPage(int page, int size, List<Todo>paginatedTodos){
        List<Todo> todos = repository.findAll();
        return ((page*size)+paginatedTodos.size())< todos.size();
    }

    public Stream<Todo> searchBy(String name,String status, String priority){
        return repository.searchBy(name,status, priority);
    }

    public Todo createTodo(TodoDto dto){
        return this.repository.create(dto);
    }

    public boolean delete(int id ){
        return repository.delete(id);
    }

    public Todo update(int id, TodoDto dto){
        return repository.update(id, dto);
    }

    public DoneUndoneResponseDto markDone(int id){
        DoneUndoneResponseDto response = new DoneUndoneResponseDto();
        Todo todo =  repository.markDone(id);
        response.setTodo(todo);
        response.setTimeStatistics(getTimeStatistics());
        return response;
    }

    public DoneUndoneResponseDto markUndone(int id){
        DoneUndoneResponseDto response = new DoneUndoneResponseDto();
        
        Todo todo =  repository.markUndone(id);
        response.setTodo(todo);
        response.setTimeStatistics(getTimeStatistics());
        return response;
    }

    public TimeStatisticsDto getTimeStatistics(){
        List<Todo> todos =repository.findAll();
        long all_seconds = 0;
        long high_seconds =0;
        long med_seconds = 0;
        long low_seconds = 0;

        int all_amount = 0;
        int  low_amount =0;
        int  high_amount = 0;
        int  med_amount = 0;

        for (Todo todo : todos) {
            
            if(todo.getCompletedAt() !=null){

                Duration duration = Duration.between(todo.getCreatedAt(), todo.getCompletedAt());
                all_seconds+= duration.toSeconds();
                all_amount++;
                if(todo.getPriority() == Priority.HIGH){
                    high_seconds+=duration.toSeconds();
                    high_amount++;
                }else if(todo.getPriority() == Priority.MEDIUM){
                    med_seconds+=duration.toSeconds();
                    med_amount++;
                }else if(todo.getPriority() == Priority.LOW){
                    low_seconds+=duration.toSeconds();
                    low_amount++;
                }
            }
        }
    long all_avg = (all_amount == 0) ? 0 :all_seconds/all_amount;
    long low_avg = (low_amount == 0) ? 0 : low_seconds / low_amount;
    long med_avg = (med_amount == 0) ? 0 : med_seconds / med_amount;
    long high_avg = (high_amount == 0) ? 0 : high_seconds / high_amount;

        String all = all_avg/60+":"+all_avg%60;
        String low = low_avg/60+":"+low_avg%60;
        String med = med_avg/60+":"+med_avg%60;
        String high = high_avg/60+":"+high_avg%60;

        TimeStatisticsDto statisticsDto = new TimeStatisticsDto(all, low, med, high);
        
        return statisticsDto;

    }
}
