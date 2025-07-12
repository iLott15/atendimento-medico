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