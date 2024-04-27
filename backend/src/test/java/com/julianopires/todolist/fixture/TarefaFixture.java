package com.julianopires.todolist.fixture;


import com.julianopires.todolist.dto.request.StatusTarefaDTO;
import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;

import java.util.Date;

public class TarefaFixture {

    public static TarefaDTO criarTarefa() {

        return TarefaDTO.builder()
                .titulo("Título da tarefa")
                .descricao("Descrição da tarefa")
                .status(StatusTarefaDTO.PENDENTE)
                .build();

    }

    public static TarefaDTO criarTarefa2() {

        return TarefaDTO.builder()
                .titulo("Título da tarefa editada")
                .descricao("Descrição da tarefa editada")
                .status(StatusTarefaDTO.CONCLUIDA)
                .build();

    }

    public static TarefaResponseDTO criarTarefaResponseEdicao(Long id) {

        return TarefaResponseDTO.builder()
                .id(id)
                .titulo("Título da tarefa editada")
                .descricao("Descrição da tarefa editada")
                .dataCriacao(new Date(2022 - 1900, 3, 22, 15, 30, 0))
                .dataConclusao(new Date(2022 - 1900, 3, 26, 15, 30, 0))
                .status(StatusTarefaDTO.CONCLUIDA)
                .build();

    }
}
