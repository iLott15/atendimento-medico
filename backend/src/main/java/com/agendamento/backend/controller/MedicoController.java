package com.agendamento.backend.controller;

import com.agendamento.backend.model.Medico;
import com.agendamento.backend.service.MedicoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> listar() {
        return medicoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Medico> criar(@RequestBody @Valid Medico medico) {
        Medico salvo = medicoService.salvar(medico);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody @Valid Medico medicoAtualizado) {
        Medico medico = medicoService.atualizar(id, medicoAtualizado);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/{id}")
    public Medico buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        medicoService.deletar(id);
    }
}