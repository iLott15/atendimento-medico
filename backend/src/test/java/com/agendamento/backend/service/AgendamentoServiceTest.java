package com.agendamento.backend.service;

import com.agendamento.backend.model.Agendamento;
import com.agendamento.backend.model.Medico;
import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.repository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AgendamentoServiceTest {

    private AgendamentoRepository agendamentoRepository;
    private AgendamentoService agendamentoService;

    @BeforeEach
    public void setUp() {
        agendamentoRepository = mock(AgendamentoRepository.class);
        agendamentoService = new AgendamentoService(agendamentoRepository);
    }

    @Test
    public void deveSalvarAgendamentoComSucesso() {
        Agendamento agendamento = criarAgendamento();

        when(agendamentoRepository.existsByMedicoIdAndData(
                agendamento.getMedico().getId(), agendamento.getData()))
                .thenReturn(false);

        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);

        Agendamento salvo = agendamentoService.salvar(agendamento);

        assertNotNull(salvo);
        assertEquals("Consulta de rotina", salvo.getDescricao());
    }

    @Test
    public void deveLancarExcecaoQuandoHorarioConflita() {
        Agendamento agendamento = criarAgendamento();

        when(agendamentoRepository.existsByMedicoIdAndData(
                agendamento.getMedico().getId(), agendamento.getData()))
                .thenReturn(true);

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            agendamentoService.salvar(agendamento);
        });

        assertEquals("Já existe um agendamento para este médico neste horário.", excecao.getMessage());
    }

    @Test
    public void deveDeletarEmMassaComSucesso() {
        List<Long> ids = List.of(1L, 2L, 3L);

        agendamentoService.deletarEmMassa(ids);

        verify(agendamentoRepository, times(1)).deleteAllById(ids);
    }

    private Agendamento criarAgendamento() {
        Medico medico = new Medico();
        medico.setId(1L);

        Paciente paciente = new Paciente();
        paciente.setId(1L);

        Agendamento agendamento = new Agendamento();
        agendamento.setMedico(medico);
        agendamento.setPaciente(paciente);
        agendamento.setData(LocalDateTime.of(2025, 7, 20, 10, 0));
        agendamento.setDescricao("Consulta de rotina");
        agendamento.setMotivo("Check-up");

        return agendamento;
    }
}