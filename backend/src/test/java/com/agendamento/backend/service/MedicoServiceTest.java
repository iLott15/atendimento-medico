package com.agendamento.backend.service;

import com.agendamento.backend.model.Medico;
import com.agendamento.backend.repository.MedicoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class MedicoServiceTest {

    private MedicoRepository medicoRepository;
    private MedicoService medicoService;

    @BeforeEach
    void setup() {
        medicoRepository = mock(MedicoRepository.class);
        medicoService = new MedicoService(medicoRepository);
    }

    @Test
    void deveBuscarMedicoPorIdExistente() {
        Medico medico = new Medico();
        medico.setId(1L);
        medico.setNome("Dr. Bono Vox");

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        Medico resultado = medicoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Dr. Bono Vox", resultado.getNome());
    }
}