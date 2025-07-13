package com.agendamento.backend.controller;

import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import com.agendamento.backend.dto.PacienteResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Page<PacienteResponseDTO>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var page = pacienteService.listar(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public Paciente criar(@RequestBody Paciente paciente) {
        return pacienteService.salvar(paciente);
    }

    @PostMapping("/em-lote")
    public List<Paciente> criarEmLote(@RequestBody List<Paciente> pacientes) {
        return pacientes.stream()
                .map(pacienteService::salvar)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        pacienteAtualizado.setId(id);
        return pacienteService.salvar(pacienteAtualizado);
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}