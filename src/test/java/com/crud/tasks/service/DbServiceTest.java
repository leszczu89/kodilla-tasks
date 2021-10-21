package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void shouldGetAllTasks() {
        //Given
        Task task1 = new Task(1L, "name", "desc");
        Task task2 = new Task(2L, "title", "content");

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));
        //When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertEquals(2, tasks.size());
    }

    @Test
    void shouldGetTask() {
        //Given
        Task task = new Task();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        //When
        Optional<Task> returnedTask = dbService.getTask(1L);
        //Then
        assertNotNull(returnedTask);
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task task = new Task();

        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals(task, savedTask);
    }

    @Test
    void shouldDeleteTask() {
        //Given

        //When
        dbService.deleteTask(1L);
        //Then
        verify(taskRepository, times(1)).deleteById(1L);
    }

}