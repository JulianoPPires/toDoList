package com.julianopires.todolist.dto.response;

import com.julianopires.todolist.dto.request.StatusTarefaDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusTarefaDTO status;
}
