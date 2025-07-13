package com.agendamento.backend.controller;

import com.agendamento.backend.dto.MedicoResponseDTO;
import com.agendamento.backend.model.Medico;
import com.agendamento.backend.service.MedicoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<MedicoResponseDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String especialidade,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<MedicoResponseDTO> resultado;

        if (nome != null) {
            resultado = medicoService.buscarPorNome(nome, pageable);
        } else if (especialidade != null) {
            resultado = medicoService.buscarPorEspecialidade(especialidade, pageable);
        } else {
            resultado = medicoService.listar(pageable);
        }

        return ResponseEntity.ok(resultado);
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
        return medicoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        medicoService.deletar(id);
    }
}