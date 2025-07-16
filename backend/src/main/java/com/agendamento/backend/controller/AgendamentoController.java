package com.agendamento.backend.controller;

import com.agendamento.backend.dto.AgendamentoDTO;
import com.agendamento.backend.dto.AgendamentoRequestDTO;
import com.agendamento.backend.dto.AgendamentoResponseDTO;
import com.agendamento.backend.model.Agendamento;
import com.agendamento.backend.model.Medico;
import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.service.AgendamentoService;
import com.agendamento.backend.service.MedicoService;
import com.agendamento.backend.service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/criar")
    public Agendamento criar(@RequestBody @Valid AgendamentoDTO dto) {
        Medico medico = medicoService.buscarPorId(dto.getMedicoId());
        Paciente paciente = pacienteService.buscarPorId(dto.getPacienteId());

        Agendamento agendamento = new Agendamento();
        agendamento.setMedico(medico);
        agendamento.setPaciente(paciente);
        agendamento.setData(dto.getData());
        agendamento.setDescricao(dto.getDescricao());
        agendamento.setMotivo(dto.getMotivo());

        return agendamentoService.salvar(agendamento);
    }

    @PostMapping("/agendar")
    public ResponseEntity<AgendamentoResponseDTO> agendar(@RequestBody @Valid AgendamentoRequestDTO dto) {
        AgendamentoResponseDTO response = agendamentoService.agendar(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public Agendamento atualizar(@PathVariable Long id, @RequestBody Agendamento agendamentoAtualizado) {
        agendamentoAtualizado.setId(id);
        return agendamentoService.salvar(agendamentoAtualizado);
    }

    @GetMapping
    public List<AgendamentoResponseDTO> listar() {
        return agendamentoService.listarTodos().stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    @GetMapping("/filtrar")
    public List<AgendamentoResponseDTO> filtrar(
            @RequestParam(required = false) Long medicoId,
            @RequestParam(required = false) Long pacienteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        return agendamentoService.filtrar(medicoId, pacienteId, data).stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
    }

    @DeleteMapping("/delete-massa")
    public ResponseEntity<Void> deletarEmMassa(@RequestBody List<Long> ids) {
        agendamentoService.deletarEmMassa(ids);
        return ResponseEntity.noContent().build();
    }
}