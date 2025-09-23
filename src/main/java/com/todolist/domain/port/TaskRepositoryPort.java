package com.todolist.domain.port;

import com.todolist.domain.model.Task;

import java.util.List;
import java.util.Optional;

//portas (interface)  "CRUD"
//aqui enxerga o repositorio.
// o domain só ver essa interface, nao enxerga o banco dados.
public interface TaskRepositoryPort {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(Long id);
    void deleteById(Long id);
}
