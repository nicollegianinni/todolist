package com.todolist.application;
//casos de uso - regras de negocio

import com.todolist.domain.model.Task;
import com.todolist.domain.port.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepositoryPort repository;

    public TaskService(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        task.setStatus("PENDING");
        return repository.save(task);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task update(Long id, Task task) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(task.getTitle());
                    existing.setDescription(task.getDescription());
                    existing.setStatus(task.getStatus());
                    existing.setDueDate(task.getDueDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
