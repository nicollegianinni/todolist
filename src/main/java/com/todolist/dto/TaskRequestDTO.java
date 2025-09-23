package com.todolist.dto;

import jakarta.validation.constraints.NotBlank;

//Adapter - é um objeto usado para receber dados da requisição do cliente
//é a entrada quando um clinte manda um JSON (com titulo e descrição da tarefa)
// Tem validação @NotBlank nao pode ser nulo nem vazio
public class TaskRequestDTO {
    @NotBlank  // validação: nao pode ser nulo nem vazio
    private String title;
    private String description;
    private String dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}
