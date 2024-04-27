package com.julianopires.todolist.contoller;

import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;
import com.julianopires.todolist.fixture.TarefaControllerFixture;
import com.julianopires.todolist.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class TarefaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private TarefaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    @Test
    public void testRotaVizualizarTarefas() throws Exception {
        this.mockMvc.perform(get("/api/v1/tarefas/visualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testRotaAdicionarTarefas() throws Exception {
        this.mockMvc.perform(post("/api/v1/tarefas/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TarefaControllerFixture.jsonAdicionarTarefa())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(TarefaControllerFixture.retoronoJsonAdicionarTarefa()));
    }

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