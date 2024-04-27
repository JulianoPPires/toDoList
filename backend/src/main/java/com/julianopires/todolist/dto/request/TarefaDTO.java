package com.julianopires.todolist.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    @NotBlank(message = "O campo 'título' não pode ser nulo ou vazio.")
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusTarefaDTO status;
}
