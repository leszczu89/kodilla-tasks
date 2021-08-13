package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final DbService service;
    private final TaskMapper taskMapper;

    @RequestMapping(value = "getTasks", method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "getTask")
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping(value = "deleteTask")
    public void deleteTask(Long taskId) {

    }

    @PutMapping(value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping("createTask")
    public void createTask(TaskDto taskDto) {

    }

    @GetMapping("getTaskById")
    public Task getTaskById(@RequestParam Long id) {
        List<Task> taskList = service.getAllTasks();
        return taskList.stream().filter(task -> task.getId().equals(id)).findAny().orElseThrow();

    }
}
