package com.julianopires.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Entity(name="tarefa")
@Table(name="tarefa")
@AllArgsConstructor
@Data
@Builder
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
}
