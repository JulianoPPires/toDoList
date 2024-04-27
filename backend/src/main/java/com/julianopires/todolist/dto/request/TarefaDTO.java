package com.julianopires.todolist.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String titulo;
    private String descricao;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dataCriacao;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusTarefaDTO status = StatusTarefaDTO.PENDENTE;
}
