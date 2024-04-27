package com.julianopires.todolist.contoller;

import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;
import com.julianopires.todolist.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TarefaControllerTest {

    @Mock
    private TarefaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    @Test
    void visualizarTarefas_DeveRetornarListaDeTarefas() {
        List<TarefaResponseDTO> tarefas = new ArrayList<>();

        when(tarefaService.buscarTodasTarefas()).thenReturn(tarefas);

        ResponseEntity<List<TarefaResponseDTO>> responseEntity = tarefaController.visualizarTarefas();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tarefas, responseEntity.getBody());
    }

    @Test
    void adicionarTarefa_DeveRetornarStatusCreatedQuandoTarefaAdicionadaComSucesso() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO();
        when(tarefaService.criarTarefa(any(TarefaDTO.class))).thenReturn(tarefaResponseDTO);

        ResponseEntity<TarefaResponseDTO> responseEntity = tarefaController.adicionarTarefa(tarefaDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(tarefaResponseDTO, responseEntity.getBody());
    }

    @Test
    void editarTarefa_DeveRetornarStatusOkQuandoTarefaEditadaComSucesso() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO();
        when(tarefaService.editarTarefa(any(TarefaDTO.class), anyLong())).thenReturn(tarefaResponseDTO);

        ResponseEntity<TarefaResponseDTO> responseEntity = tarefaController.editarTarefa(tarefaDTO, 1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tarefaResponseDTO, responseEntity.getBody());
    }

    @Test
    void editarTarefa_DeveRetornarStatusNotFoundQuandoTarefaNaoEncontrada() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        when(tarefaService.editarTarefa(any(TarefaDTO.class), anyLong())).thenThrow(EntityNotFoundException.class);

        ResponseEntity<TarefaResponseDTO> responseEntity = tarefaController.editarTarefa(tarefaDTO, 1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void excluirTarefa_DeveRetornarStatusNoContentQuandoTarefaExcluidaComSucesso() {

        ResponseEntity<TarefaResponseDTO> responseEntity = tarefaController.excluirTarefa(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void excluirTarefa_DeveRetornarStatusNotFoundQuandoTarefaNaoEncontrada() {
        doThrow(EntityNotFoundException.class).when(tarefaService).removerTarefa(anyLong());

        ResponseEntity<TarefaResponseDTO> responseEntity = tarefaController.excluirTarefa(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}