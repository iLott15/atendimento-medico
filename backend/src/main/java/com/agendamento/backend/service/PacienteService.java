package com.agendamento.backend.service;

import com.agendamento.backend.dto.PacienteResponseDTO;
import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Page<PacienteResponseDTO> listar(Pageable pageable) {
        return pacienteRepository.findAll(pageable)
                .map(PacienteResponseDTO::new);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}