package com.todolist.domain.port;

import com.todolist.domain.model.Task;

import java.util.List;
import java.util.Optional;

//portas (interface)
public interface TaskRepositoryPort {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(Long id);
    void deleteById(Long id);
}
