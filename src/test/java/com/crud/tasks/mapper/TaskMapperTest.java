package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(task);
        assertEquals(1L, task.getId());
    }

    @Test
    void shouldMapToTaskDto() {
        //Given
        Task task = new Task();
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(taskDto);
        assertNull(taskDto.getId());
    }

    @Test
    void shouldMapToEmptyTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertNotNull(taskDtos);
        assertEquals(0, taskDtos.size());
    }

}