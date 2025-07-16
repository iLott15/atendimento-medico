package com.agendamento.backend.controller;

import com.agendamento.backend.dto.AgendamentoRequestDTO;
import com.agendamento.backend.dto.AgendamentoResponseDTO;
import com.agendamento.backend.model.Agendamento;
import com.agendamento.backend.model.Medico;
import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.service.AgendamentoService;
import com.agendamento.backend.service.MedicoService;
import com.agendamento.backend.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AgendamentoController.class, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
public class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamentoService agendamentoService;

    @MockBean
    private MedicoService medicoService;

    @MockBean
    private PacienteService pacienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveAgendarComSucesso() throws Exception {
        var dto = new AgendamentoRequestDTO(
                1L,
                2L,
                LocalDateTime.now().plusDays(1),
                "Rotina",
                "Consulta");

        // Criando o agendamento com médico e paciente
        var agendamento = new Agendamento();
        agendamento.setId(10L);
        agendamento.setData(dto.data());
        agendamento.setDescricao("Consulta");
        agendamento.setMotivo("Rotina");

        var medico = new Medico();
        medico.setNome("Dr. House");
        agendamento.setMedico(medico);

        var paciente = new Paciente();
        paciente.setNome("Gregory");
        agendamento.setPaciente(paciente);

        var response = new AgendamentoResponseDTO(agendamento);

        when(agendamentoService.agendar(any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/agendamentos/agendar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.descricao").value("Consulta"))
                .andExpect(jsonPath("$.motivo").value("Rotina"))
                .andExpect(jsonPath("$.medicoNome").value("Dr. House"))
                .andExpect(jsonPath("$.pacienteNome").value("Gregory"));
    }

    @Test
    void deveFiltrarAgendamentos() throws Exception {
        LocalDate data = LocalDate.of(2025, 7, 20);

        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setData(LocalDateTime.of(2025, 7, 20, 10, 0));
        agendamento.setDescricao("Exame");
        agendamento.setMotivo("Rotina");

        Medico medico = new Medico();
        medico.setNome("Dr. Cox");
        agendamento.setMedico(medico);

        Paciente paciente = new Paciente();
        paciente.setNome("JD");
        agendamento.setPaciente(paciente);

        when(agendamentoService.filtrar(1L, null, data)).thenReturn(List.of(agendamento));

        mockMvc.perform(MockMvcRequestBuilders.get("/agendamentos/filtrar")
                .param("medicoId", "1")
                .param("data", "2025-07-20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Exame"))
                .andExpect(jsonPath("$[0].motivo").value("Rotina"))
                .andExpect(jsonPath("$[0].medicoNome").value("Dr. Cox"))
                .andExpect(jsonPath("$[0].pacienteNome").value("JD"));
    }

    @Test
    void deveDeletarEmMassa() throws Exception {
        List<Long> ids = List.of(1L, 2L, 3L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/agendamentos/delete-massa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ids)))
                .andExpect(status().isNoContent());

        verify(agendamentoService, times(1)).deletarEmMassa(ids);
    }
}