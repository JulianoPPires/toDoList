package com.julianopires.todolist.contoller;

import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;
import com.julianopires.todolist.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/visualizar")
    public ResponseEntity<List<TarefaResponseDTO>> visualizarTarefas() {
        List<TarefaResponseDTO> tarefasDTO = tarefaService.buscarTodasTarefas();
        return ResponseEntity.ok(tarefasDTO);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<TarefaResponseDTO> adicionarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO) {
        return new ResponseEntity<>(tarefaService.criarTarefa(tarefaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<TarefaResponseDTO> editarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO, @NotBlank @PathVariable Long id) {
        try {
            TarefaResponseDTO tarefaResponseDTO = tarefaService.editarTarefa(tarefaDTO, id);
            return ResponseEntity.ok(tarefaResponseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<TarefaResponseDTO> excluirTarefa(@PathVariable Long id) {
        try {
            tarefaService.removerTarefa(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
