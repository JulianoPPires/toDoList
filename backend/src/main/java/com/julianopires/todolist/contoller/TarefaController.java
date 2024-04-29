package com.julianopires.todolist.contoller;

import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;
import com.julianopires.todolist.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Visualizar todas tarefas")
    @GetMapping()
    public ResponseEntity<List<TarefaResponseDTO>> visualizarTarefas() {
        List<TarefaResponseDTO> tarefasDTO = tarefaService.buscarTodasTarefas();
        return ResponseEntity.ok(tarefasDTO);
    }

    @Operation(summary = "Adicionar tarefa")
    @PostMapping()
    public ResponseEntity<TarefaResponseDTO> adicionarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO) {
        return new ResponseEntity<>(tarefaService.criarTarefa(tarefaDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Editar tarefa")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> editarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO, @PathVariable Long id) {
        try {
            TarefaResponseDTO tarefaResponseDTO = tarefaService.editarTarefa(tarefaDTO, id);
            return ResponseEntity.ok(tarefaResponseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir tarefa")
    @DeleteMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> excluirTarefa(@PathVariable Long id) {
        try {
            tarefaService.removerTarefa(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
