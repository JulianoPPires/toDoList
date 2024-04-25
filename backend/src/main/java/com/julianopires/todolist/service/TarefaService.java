package com.julianopires.todolist.service;

import com.julianopires.todolist.dto.request.TarefaDTO;
import com.julianopires.todolist.dto.response.TarefaResponseDTO;
import com.julianopires.todolist.model.Tarefa;
import com.julianopires.todolist.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public TarefaResponseDTO editarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = modelMapper.map(tarefaDTO, Tarefa.class);
        Optional<Tarefa> optionalTarefa = this.tarefaRepository.findById(tarefa.getId());
        if (optionalTarefa.isPresent()) {
            Tarefa tarefaEditada = optionalTarefa.get();
            tarefaEditada.setTitulo(tarefa.getTitulo());
            tarefaEditada.setDescricao(tarefa.getDescricao());
            tarefaEditada.setDataCriacao(tarefa.getDataCriacao());
            tarefaEditada.setDataConclusao(tarefa.getDataConclusao());
            tarefaEditada.setStatus(tarefa.getStatus());
            Tarefa tarefaSalva = tarefaRepository.save(tarefaEditada);
            return modelMapper.map(tarefaSalva, TarefaResponseDTO.class);
        } else {
            throw new EntityNotFoundException();
        }

    }

    public void removerTarefa(Long id) {
        if (this.tarefaRepository.existsById(id)) {
            this.tarefaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}

