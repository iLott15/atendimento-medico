package com.agendamento.backend.controller;

import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes") // Ex: http://localhost:8080/pacientes
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listarTodos();
    }

    @PostMapping("/em-lote")
    public List<Paciente> criarEmLote(@RequestBody List<Paciente> pacientes) {
        return pacientes.stream()
                .map(pacienteService::salvar)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}