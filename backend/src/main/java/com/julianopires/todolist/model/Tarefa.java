package com.julianopires.todolist.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name="tarefa")
@Table(name="tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Nullable
    private Date dataCriacao;
    @Nullable
    private Date dataConclusao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
}
