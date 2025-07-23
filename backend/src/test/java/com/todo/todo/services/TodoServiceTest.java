package com.todo.todo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.todo.todo.models.Todo;
import com.todo.todo.repository.TodoRepositoryImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepositoryImpl repository;

    @InjectMocks
    private TodoService todoService;

    private List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(1, "Buy groceries", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(2, "Finish project", "Low",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(3, "i don't know", "medium",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(4, "buy chips", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(5, "pet the cat", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
            new Todo(6, "feed the cat", "High",LocalDateTime.parse("2025-07-20T11:30:00")),
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
            new Todo(18, "buy type shi", "High",LocalDateTime.parse("2025-07-20T11:30:00"))

    ));


    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
        "0, 2, 2",
        "0, 2, 2",
        "9, 2, 0"
    })
    public void getAllTodos(int page, int size, int page_expected_size){

        List<Todo>expectedSizeList = new ArrayList<>();
        for(int i = 0; i<page_expected_size; i++){
            expectedSizeList.add(new Todo(i, "null", "High",LocalDateTime.now()));
        }
        
        when(repository.findAll()).thenReturn(todos);
        //given(repository.findAll()).willReturn(new ArrayList<Todo>());
        List<Todo> result = this.todoService.getAllTodos(page, size);

        System.out.println("ksndsdsdksk"+result);
        System.out.println("el otro jssjajshjaja omehfhs. skjsdhsj. sjhdhjkjks.  sdnsdjszdjahh. "+expectedSizeList.size());
        assertEquals(page_expected_size, result.size());
    }
    @Test
    void getAllTodos_shouldReturnAllTodosWhenDefaultPaginationValues() {

        int default_page = 0;
        int default_size =9;
        
        when(repository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos(default_page,default_size);

        assertEquals(default_size, result.size());
    }
}