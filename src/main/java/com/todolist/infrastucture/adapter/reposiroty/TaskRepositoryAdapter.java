package com.todolist.infrastucture.adapter.reposiroty;

import com.todolist.domain.model.Task;
import com.todolist.domain.port.TaskRepositoryPort;
import com.todolist.infrastucture.entity.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// adaptadores - conexão com banco de dados

@Repository
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository jpaRepository;

    public TaskRepositoryAdapter(TaskJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setStatus(task.getStatus());
        entity.setDueDate(task.getDueDate());

        TaskEntity saved = jpaRepository.save(entity);
        return new Task(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getStatus(), saved.getDueDate());
    }

    @Override
    public List<Task> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Task(e.getId(), e.getTitle(), e.getDescription(), e.getStatus(), e.getDueDate()))
                .toList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new Task(e.getId(), e.getTitle(), e.getDescription(), e.getStatus(), e.getDueDate()));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
