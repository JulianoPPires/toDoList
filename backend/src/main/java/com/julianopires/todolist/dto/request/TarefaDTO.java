package com.julianopires.todolist.dto.request;

import com.julianopires.todolist.model.StatusTarefa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TarefaDTO {
    @NotBlank(message = "O campo 'título' não pode ser nulo ou vazio.")
    private String titulo;
    @NotBlank(message = "O campo 'descricao' não pode ser nulo ou vazio.")
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
}
