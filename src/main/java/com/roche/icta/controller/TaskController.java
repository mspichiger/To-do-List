package com.roche.icta.controller;

import com.roche.icta.model.Task;
import com.roche.icta.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @Operation(operationId = "findalltasks", summary = "")
    @Tag(name = "getAllTask", description = "find all tasks")
    @GetMapping
    public ResponseEntity<List<Task>> read() {
        List<Task> tasks = service.getAllTasks();
        return ResponseEntity.ok(tasks);

    }

    @Tag(name = "getAllTaskById", description = "Get Task by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Task> readById(@PathVariable Long id) {
        Optional<Task> task = service.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @Tag(name = "createTask", description = "Create Task")
    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody Task task) {
        Task created = service.createTask(task);
        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    @Tag(name = "updateTask", description = "Update Task")
    @PatchMapping("/{id}")
    public ResponseEntity<Task> update(@Valid @PathVariable Long id, @RequestBody Task details) {
        Optional<Task> update = service.updateTask(id, details);
        if (update.isPresent()) {
            return ResponseEntity.ok(update.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @Tag(name = "delteAllTaskById", description = "Delete Task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        Optional<Task> task = service.getTaskById(id);
        if (task.isPresent()) {
            service.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}