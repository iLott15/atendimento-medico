package com.agendamento.backend.service;

import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PacienteServiceTest {

    private PacienteRepository pacienteRepository;
    private PacienteService pacienteService;

    @BeforeEach
    public void setUp() {
        pacienteRepository = mock(PacienteRepository.class);
        pacienteService = new PacienteService(pacienteRepository);
    }

    @Test
    public void deveBuscarPacientePorIdExistente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("João");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente resultado = pacienteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    public void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        when(pacienteRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            pacienteService.buscarPorId(999L);
        });

        assertEquals("Paciente não encontrado", excecao.getMessage());
    }
}