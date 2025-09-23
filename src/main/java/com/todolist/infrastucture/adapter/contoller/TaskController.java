package com.todolist.infrastucture.adapter.contoller;

import com.todolist.application.TaskService;
import com.todolist.domain.model.Task;
import com.todolist.dto.TaskRequestDTO;
import com.todolist.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// adaptador - endpopint metodos http fazendo a coneção com o mundo externo
//entry point da aplicação pelo metodo HTTP (API REST)
//converte DTOs vindos do cliente (externo) em Task (domain)
//Converte Task em TaskResponseDTO antes de devolver para o cliente


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO dto) {
        Task task = new Task(null, dto.getTitle(), dto.getDescription(), "Task pendente", dto.getDueDate());
        Task saved = service.create(task);
        return ResponseEntity.ok(new TaskResponseDTO(saved.getId(), saved.getTitle(), saved.getDescription(),
                //Retornando apenas o status: "Task criada! Pendente a fazer" e a data de expiração, para o cliente final externo
                saved.getStatus(), saved.getDueDate()));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll() {
        List<TaskResponseDTO> tasks = service.getAll().stream()
                .map(t -> new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), t.getDueDate()))
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TaskRequestDTO dto) {
        Task updated = service.update(id, new Task(id, dto.getTitle(), dto.getDescription(), "Task criada! Pendente a fazer", dto.getDueDate()));
        return ResponseEntity.ok(new TaskResponseDTO(updated.getId(), updated.getTitle(), updated.getDescription(),
                updated.getStatus(), updated.getDueDate()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
