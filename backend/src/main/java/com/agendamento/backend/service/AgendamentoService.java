package com.agendamento.backend.service;

import com.agendamento.backend.model.Agendamento;
import com.agendamento.backend.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> filtrar(Long medicoId, Long pacienteId, LocalDate data) {
        if (medicoId == null && pacienteId == null && data == null) {
            return agendamentoRepository.findAll();
        }

        return agendamentoRepository.findByFiltros(medicoId, pacienteId, data);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    public Agendamento salvar(Agendamento agendamento) {
        boolean conflito = agendamentoRepository.existsByMedicoIdAndData(
                agendamento.getMedico().getId(),
                agendamento.getData());

        if (conflito) {
            throw new RuntimeException("Já existe um agendamento para este médico neste horário.");
        }

        return agendamentoRepository.save(agendamento);
    }

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public void deletar(Long id) {
        agendamentoRepository.deleteById(id);
    }

    public void deletarEmMassa(List<Long> ids) {
        agendamentoRepository.deleteAllById(ids);
    }
}