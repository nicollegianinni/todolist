package com.todolist.application;

import com.todolist.domain.model.Task;
import com.todolist.domain.port.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

//orquestra os casos de uso - regras de negocio
// quando cria um service pense o que essa aplicação pode fazer... cada resposta sera cada metodo

@Service
public class TaskService {

    private final TaskRepositoryPort repository;

    public TaskService(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        task.setStatus("Task criada! Pendente a fazer");
        return repository.save(task);
    }
//lista todas tarefas
    public List<Task> getAll() {
        return repository.findAll();
    }

    //atualiza tarefas
    public Task update(Long id, Task task) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(task.getTitle());
                    existing.setDescription(task.getDescription());
                    existing.setStatus(task.getStatus());
                    existing.setDueDate(task.getDueDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Essa tarefa nao existe!"));
    }

//deleta tarefas
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
