package com.julianopires.todolist.service;

import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;
import com.julianopires.todolist.model.StatusTarefa;
import com.julianopires.todolist.model.Tarefa;
import com.julianopires.todolist.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    private final ModelMapper modelMapper;

    public TarefaService(TarefaRepository tarefaRepository, ModelMapper modelMapper) {
        this.tarefaRepository = tarefaRepository;
        this.modelMapper = modelMapper;
    }

    public List<TarefaResponseDTO> buscarTodasTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(tarefa -> modelMapper.map(tarefa, TarefaResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TarefaResponseDTO criarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = modelMapper.map(tarefaDTO, Tarefa.class);
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return modelMapper.map(tarefaSalva, TarefaResponseDTO.class);
    }

    @Transactional
    public TarefaResponseDTO editarTarefa(TarefaDTO tarefaDTO, Long id) {
        Optional<Tarefa> optionalTarefa = this.tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            if (tarefaDTO.getTitulo() != null && !tarefaDTO.getTitulo().equals(tarefaExistente.getTitulo())) {
                tarefaExistente.setTitulo(tarefaDTO.getTitulo());
            }
            if (tarefaDTO.getDescricao() != null && !tarefaDTO.getDescricao().equals(tarefaExistente.getDescricao())) {
                tarefaExistente.setDescricao(tarefaDTO.getDescricao());
            }
            if (tarefaDTO.getStatus() != null && !tarefaDTO.getStatus().name().equals(tarefaExistente.getStatus().name())) {
                tarefaExistente.setStatus(StatusTarefa.valueOf(tarefaDTO.getStatus().name()));
            }
            if (tarefaDTO.getDataCriacao() != null && !tarefaDTO.getDataCriacao().equals(tarefaExistente.getDataCriacao())) {
                tarefaExistente.setDataCriacao(tarefaDTO.getDataCriacao());
            }
            if (tarefaDTO.getDataConclusao() != null && !tarefaDTO.getDataConclusao().equals(tarefaExistente.getDataConclusao())) {
                tarefaExistente.setDataConclusao(tarefaDTO.getDataConclusao());
            }
            return modelMapper.map(this.tarefaRepository.save(tarefaExistente), TarefaResponseDTO.class);
        } else {
            throw new EntityNotFoundException();
        }

    }

    public void removerTarefa(Long id) {
        if (this.tarefaRepository.existsById(id)) {
            this.tarefaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Tarefa com o ID " + id + " não encontrada");
        }
    }


}

