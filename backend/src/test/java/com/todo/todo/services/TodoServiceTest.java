package com.todo.todo.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;
import com.todo.todo.repository.TodoRepositoryImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepositoryImpl repository;

    @InjectMocks
    private TodoService todoService;

    private List<Todo>todos;

    
    
    @BeforeEach
    void setUp() {
        todos = new ArrayList<>(Arrays.asList(
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


    }

  //  @ParameterizedTest
  //  @CsvSource({
  //      "0, 2, 2",
  //      "2, 4, 4",
  //      "9, 2, 0"
  //  })
  //  public void getAllTodos(int page, int size, int page_expected_size){
//
  //      List<Todo>expectedSizeList = new ArrayList<>();
  //      for(int i = 0; i<page_expected_size; i++){
  //          expectedSizeList.add(new Todo(i, "null", "High",LocalDateTime.now()));
  //      }
  //      
  //      when(repository.findAll()).thenReturn(todos);
  //      //given(repository.findAll()).willReturn(new ArrayList<Todo>());
  //      List<Todo> result = this.todoService.getAllTodos(page, size);
//
  //      assertEquals(page_expected_size, result.size());
  //  }


  
   // @Test
   // void getAllTodos_shouldReturnAllTodosWithDefaultPaginationValues() {
//
   //     int default_page = 0;
   //     int default_size =9;
   //     
   //     when(repository.findAll()).thenReturn(todos);
//
   //     List<Todo> result = todoService.getAllTodos(default_page,default_size);
//
   //     assertEquals(default_size, result.size());
   // }

    
    @Test
    public void create_with_due_date(){
        TodoDto dto = new TodoDto();
        dto.name = "damn that's a crazy task dude";
        dto.priority = "High";
        dto.due_date = LocalDateTime.parse("2025-07-20T11:30:00");
        when(repository.create(dto)).thenReturn(new Todo(20, dto.name, dto.priority, dto.due_date));
        Todo result = todoService.createTodo(dto);
        Assertions.assertThat(result).isNotNull();
    }

    

    @ParameterizedTest
    @CsvSource({
        "2",
        "1",
        "8"
    })
    public void check(int id){
        Todo todo = todos.stream()
                    .filter(t -> t.id == id)
                    .findFirst().
                    orElseThrow(() ->  new IllegalStateException("Test setup error: Todo with ID " + id + " not found."));

        Todo expected_todo = new Todo(id, todo.name, todo.priority.toString(), todo.due_date);

        expected_todo.status = Status.DONE;
        when(repository.markDone(id)).thenReturn(expected_todo);
        
        Todo result = todoService.markDone(id);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.status).isEqualTo(Status.DONE);
    }

    @ParameterizedTest
    @CsvSource({
        "2",
        "1",
        "8"
    })
    public void update(int id){

        TodoDto updateDto = new TodoDto();
        updateDto.name = "Updated Task Name";
        updateDto.priority = "High";
        updateDto.due_date = LocalDateTime.parse("2025-01-01T15:00:00");
        //int id = 10;
        Todo expectedUpdatedTodo = new Todo(
            id,
            updateDto.name,
            updateDto.priority,
            updateDto.due_date 
        );

        when(repository.update(eq(id), any(TodoDto.class)))
               .thenReturn(expectedUpdatedTodo);
        


        Todo result = todoService.update(id, updateDto);
        System.out.println("sndskjdnksbdsbfmsddsndhfjhskhfjshkfkfsfkfkkjfkjfkksksfksjkskj "+result);
        Assertions.assertThat(result).isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
        "2",
        "1",
        "8"
    })
    public void delete(int id){
        when(repository.delete(id)).thenReturn(true);
        boolean result = todoService.delete(id);
        Assertions.assertThat(result).isTrue();
    }
}