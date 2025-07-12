package com.agendamento.backend.service;

import com.agendamento.backend.model.Medico;
import com.agendamento.backend.repository.MedicoRepository;

// import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

// Camada de lógica de negócio
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado com ID: " + id));

        medicoExistente.setNome(medicoAtualizado.getNome());
        medicoExistente.setEmail(medicoAtualizado.getEmail());
        medicoExistente.setCrm(medicoAtualizado.getCrm());
        medicoExistente.setEspecialidade(medicoAtualizado.getEspecialidade());

        return medicoRepository.save(medicoExistente);
    }

    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }
}