package com.agendamento.backend.controller;

import com.agendamento.backend.dto.AdminDTO;
import com.agendamento.backend.dto.AdminResponseDTO;
import com.agendamento.backend.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponseDTO> cadastrar(@RequestBody @Valid AdminDTO dto) {
        var admin = adminService.cadastrar(dto);
        return ResponseEntity.ok(admin);
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> listarTodos() {
        return ResponseEntity.ok(adminService.listarTodos());
    }
}