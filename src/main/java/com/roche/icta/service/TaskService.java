package com.roche.icta.service;

import com.roche.icta.model.Task;
import com.roche.icta.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public Task createTask(Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task details) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            Task taskfromdb = task.get();
            taskfromdb.setTitle(details.getTitle());
            taskfromdb.setDescription(details.getDescription());
            taskfromdb.setCompleted(details.isCompleted());
            return Optional.of(repository.save(taskfromdb));
        }
        return Optional.empty();
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}