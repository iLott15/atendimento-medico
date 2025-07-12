package com.agendamento.backend.service;

import com.agendamento.backend.model.Medico;
import com.agendamento.backend.repository.AgendamentoRepository;
import com.agendamento.backend.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
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
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        if (!agendamentoRepository.findByMedicoId(medico.getId()).isEmpty()) {
            throw new RuntimeException("Médico possui agendamentos e não pode ser excluído.");
        }

        medicoRepository.deleteById(medico.getId());
    }
}