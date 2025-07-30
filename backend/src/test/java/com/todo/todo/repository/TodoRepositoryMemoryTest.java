package com.todo.todo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.todo.todo.dtos.TodoDto;
import com.todo.todo.enums.Priority;
import com.todo.todo.enums.Status;
import com.todo.todo.models.Todo;

public class TodoRepositoryMemoryTest {
    private TodoRepositoryMemory repository;

    @BeforeEach
    void setUp() {
        repository = new TodoRepositoryMemory();
    }

    @Test
    void findAll_shouldReturnAllInitialTodos() {
        List<Todo> allTodos = repository.findAll();
        assertNotNull(allTodos);
        assertEquals(18, allTodos.size()); 
    }

    @ParameterizedTest
    @CsvSource({
            "buy, , , 5", 
            "project, , , 1",
            "I DON'T KNOW, , , 10", 
            "something, , , 1",
            "nonexistent, , , 0"
    })
    void searchBy_shouldFilterByName(String name, String status, String priority, long expectedCount) {
        List<Todo> result = repository.searchBy(name, status, priority).collect(Collectors.toList());
        assertEquals(expectedCount, result.size());
        if (expectedCount > 0) {
            assertTrue(result.stream().allMatch(t -> t.getName().toLowerCase().contains(name.toLowerCase())));
        }
    }

    @Test
    void searchBy_shouldFilterByStatus() {
        repository.findAll().get(0).setStatus(Status.DONE);
        repository.findAll().get(1).setStatus(Status.DONE);
        repository.findAll().get(2).setStatus(Status.UNDONE);

        List<Todo> doneTodos = repository.searchBy(null, "DONE", null).collect(Collectors.toList());
        assertEquals(2, doneTodos.size());
        assertTrue(doneTodos.stream().allMatch(t -> t.getStatus() == Status.DONE));

        List<Todo> undoneTodos = repository.searchBy(null, "UNDONE", null).collect(Collectors.toList());
       
        long expectedUndoneCount = repository.findAll().stream()
                                    .filter(t -> t.getStatus() == Status.UNDONE).count();
        assertEquals(expectedUndoneCount, undoneTodos.size());
        assertTrue(undoneTodos.stream().allMatch(t -> t.getStatus() == Status.UNDONE));


        List<Todo> allStatusTodos = repository.searchBy(null, "ALL", null).collect(Collectors.toList());
        assertEquals(18, allStatusTodos.size());
    }

    @Test
    void searchBy_shouldFilterByPriority() {
        long initialHighCount = repository.findAll().stream()
                                .filter(t -> t.getPriority() == Priority.HIGH).count();
        List<Todo> highPriorityTodos = repository.searchBy(null, null, "HIGH").collect(Collectors.toList());
        assertEquals(initialHighCount, highPriorityTodos.size());
        assertTrue(highPriorityTodos.stream().allMatch(t -> t.getPriority() == Priority.HIGH));

        List<Todo> lowPriorityTodos = repository.searchBy(null, null, "LOW").collect(Collectors.toList());
        assertEquals(2, lowPriorityTodos.size());
        assertTrue(lowPriorityTodos.stream().allMatch(t -> t.getPriority() == Priority.LOW));

        List<Todo> mediumPriorityTodos = repository.searchBy(null, null, "MEDIUM").collect(Collectors.toList());
        assertEquals(1, mediumPriorityTodos.size());
        assertTrue(mediumPriorityTodos.stream().allMatch(t -> t.getPriority() == Priority.MEDIUM));


        List<Todo> allPriorityTodos = repository.searchBy(null, null, "ALL").collect(Collectors.toList());
        assertEquals(18, allPriorityTodos.size()); 
    }

    @Test
    void searchBy_shouldCombineFilters() {
        repository.findAll().get(0).setName("Buy Important Groceries");
        repository.findAll().get(0).setStatus(Status.DONE);

        List<Todo> result = repository.searchBy("Groceries", "DONE", "HIGH").collect(Collectors.toList());
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Buy Important Groceries", result.get(0).getName());
        assertEquals(Status.DONE, result.get(0).getStatus());
        assertEquals(Priority.HIGH, result.get(0).getPriority());
    }


    @Test
    void create_shouldAddNewTodoAndAssignId() {
        TodoDto dto = new TodoDto();
        dto.name = "New Task";
        dto.priority = "Low";
        dto.due_date = LocalDateTime.now();

        int initialSize = repository.findAll().size();
        Todo newTodo = repository.create(dto);

        assertNotNull(newTodo);
        assertEquals(initialSize + 1, repository.findAll().size());
        assertEquals(initialSize + 1, newTodo.getId()); 
        assertEquals("New Task", newTodo.getName());
        assertEquals(Priority.LOW, newTodo.getPriority());
        assertEquals(Status.UNDONE, newTodo.getStatus());
    }

    @Test
    void getById_shouldReturnCorrectTodo() {
        Todo expectedTodo = repository.findAll().get(0);
        Todo foundTodo = repository.getById(1);
        assertNotNull(foundTodo);
        assertEquals(expectedTodo.getId(), foundTodo.getId());
        assertEquals(expectedTodo.getName(), foundTodo.getName());
    }

    @Test
    void getById_shouldThrowIndexOutOfBoundsForInvalidId() {
        assertThrows(IndexOutOfBoundsException.class, () -> repository.getById(0)); 
        assertThrows(IndexOutOfBoundsException.class, () -> repository.getById(999));
    }

    @Test
    void filterByDueDatePriority_shouldReturnFilteredList() {
    
        Todo todoWithSpecificPriority = new Todo(99, "Priority Specific", "LOW", LocalDateTime.now().plusDays(5));
        repository.findAll().add(todoWithSpecificPriority); 

        List<Todo> lowPriorityTodos = repository.filterByDueDatePriority(Priority.LOW, LocalDateTime.now());
        assertNotNull(lowPriorityTodos);
        assertEquals(3, lowPriorityTodos.size());
        assertTrue(lowPriorityTodos.stream().allMatch(t -> t.getPriority() == Priority.LOW));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 18, 5}) 
    void delete_shouldRemoveTodoAndReturnTrue(int idToDelete) {
        int initialSize = repository.findAll().size();
        boolean result = repository.delete(idToDelete);

        assertTrue(result);
        assertEquals(initialSize - 1, repository.findAll().size());
        Optional<Todo> deletedTodo = repository.findAll().stream()
                                        .filter(t -> t.getId() == idToDelete)
                                        .findFirst();
        assertFalse(deletedTodo.isPresent());
    }

    @Test
    void delete_shouldReturnFalseForNonExistentId() {
        int initialSize = repository.findAll().size();
        boolean result = repository.delete(999); 

        assertFalse(result);
        assertEquals(initialSize, repository.findAll().size());
    }

    @Test
    void update_shouldModifyTodoProperties() {
        int idToUpdate = 2; 
        TodoDto updateDto = new TodoDto();
        updateDto.name = "Updated Project Name";
        updateDto.priority = "HIGH";
        updateDto.due_date = LocalDateTime.parse("2026-01-01T10:00:00");

        Todo updatedTodo = repository.update(idToUpdate, updateDto);

        assertNotNull(updatedTodo);
        assertEquals(idToUpdate, updatedTodo.getId());
        assertEquals("Updated Project Name", updatedTodo.getName());
        assertEquals(Priority.HIGH, updatedTodo.getPriority());
        assertEquals(LocalDateTime.parse("2026-01-01T10:00:00"), updatedTodo.getDueDate());

        Todo storedTodo = repository.getById(idToUpdate);
        assertEquals("Updated Project Name", storedTodo.getName());
    }

    @Test
    void update_shouldHandlePartialUpdates() {
        int idToUpdate = 3; 
        TodoDto updateDto = new TodoDto();
        updateDto.name = "Only Name Changed";

        Todo updatedTodo = repository.update(idToUpdate, updateDto);

        assertNotNull(updatedTodo);
        assertEquals(idToUpdate, updatedTodo.getId());
        assertEquals("Only Name Changed", updatedTodo.getName());
        assertEquals(Priority.MEDIUM, updatedTodo.getPriority());
        assertNotNull(updatedTodo.getDueDate());
    }

    @Test
    void markDone_shouldSetStatusToDoneAndCompletedAt() {
        int idToMarkDone = 4;
        Todo todo = repository.getById(idToMarkDone);
        assertEquals(Status.UNDONE, todo.getStatus());

        Todo markedTodo = repository.markDone(idToMarkDone);

        assertNotNull(markedTodo);
        assertEquals(Status.DONE, markedTodo.getStatus());
        assertNotNull(markedTodo.getCompletedAt());
        assertEquals(Status.DONE, repository.getById(idToMarkDone).getStatus());
    }

    @Test
    void markDone_shouldNotChangeCompletedAtIfAlreadyDone() {
        int idToMarkDone = 5;
        Todo todo = repository.getById(idToMarkDone);
        todo.setStatus(Status.DONE);
        LocalDateTime initialCompletedAt = LocalDateTime.now().minusHours(1).withNano(0);
        todo.setCompletedAt(initialCompletedAt);

        Todo markedTodo = repository.markDone(idToMarkDone);

        assertNotNull(markedTodo);
        assertEquals(Status.DONE, markedTodo.getStatus());
        assertEquals(initialCompletedAt, markedTodo.getCompletedAt());
    }

    @Test
    void markUndone_shouldSetStatusToUndoneAndClearCompletedAt() {
        int idToMarkUndone = 6;
        Todo todo = repository.getById(idToMarkUndone);
        todo.setStatus(Status.DONE);
        todo.setCompletedAt(LocalDateTime.now());

        Todo unmarkedTodo = repository.markUndone(idToMarkUndone);

        assertNotNull(unmarkedTodo);
        assertEquals(Status.UNDONE, unmarkedTodo.getStatus());
        assertNull(unmarkedTodo.getCompletedAt());
        assertEquals(Status.UNDONE, repository.getById(idToMarkUndone).getStatus());
    }

    @Test
    void markUndone_shouldNotChangeIfAlreadyUndone() {
        int idToMarkUndone = 7;
        Todo todo = repository.getById(idToMarkUndone);
        assertEquals(Status.UNDONE, todo.getStatus());
        assertNull(todo.getCompletedAt());

        Todo unmarkedTodo = repository.markUndone(idToMarkUndone);

        assertNotNull(unmarkedTodo);
        assertEquals(Status.UNDONE, unmarkedTodo.getStatus());
        assertNull(unmarkedTodo.getCompletedAt());
    }
}
