package com.agendamento.backend.service;

import com.agendamento.backend.dto.AgendamentoRequestDTO;
import com.agendamento.backend.dto.AgendamentoResponseDTO;
import com.agendamento.backend.exception.HorarioIndisponivelException;
import com.agendamento.backend.model.Agendamento;
import com.agendamento.backend.repository.AgendamentoRepository;
import com.agendamento.backend.repository.MedicoRepository;
import com.agendamento.backend.repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Agendamento> filtrar(Long medicoId, Long pacienteId, LocalDate data) {
        if (medicoId == null && pacienteId == null && data == null) {
            return agendamentoRepository.findAll();
        }

        return agendamentoRepository.findByFiltros(medicoId, pacienteId, data);
    }

    public AgendamentoResponseDTO agendar(AgendamentoRequestDTO dto) {
        boolean conflito = agendamentoRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());

        if (conflito) {
            throw new HorarioIndisponivelException("Médico já possui agendamento neste horário.");
        }

        var medico = medicoRepository.findById(dto.idMedico())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        var paciente = pacienteRepository.findById(dto.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setMedico(medico);
        agendamento.setPaciente(paciente);
        agendamento.setData(dto.data());
        agendamento.setMotivo(dto.motivo());
        agendamento.setDescricao(dto.descricao());

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(salvo);
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