package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://leszczu89.github.io/")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TaskController {
    private final DbService service;
    private final TaskMapper taskMapper;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @DeleteMapping(value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        service.deleteTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }
    @PostMapping(value = "/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        return taskMapper.mapToTaskDto(service.saveTask(task));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                service.getTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
    }
}
