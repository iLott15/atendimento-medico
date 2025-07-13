package com.agendamento.backend.service;

import com.agendamento.backend.dto.AdminDTO;
import com.agendamento.backend.dto.AdminResponseDTO;
import com.agendamento.backend.model.Admin;
import com.agendamento.backend.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminResponseDTO cadastrar(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setNome(dto.nome());
        admin.setEmail(dto.email());
        admin.setTelefone(dto.telefone());

        Admin salvo = adminRepository.save(admin);

        return new AdminResponseDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getEmail(),
            salvo.getTelefone()
        );
    }

    public List<AdminResponseDTO> listarTodos() {
        return adminRepository.findAll().stream().map(admin ->
            new AdminResponseDTO(
                admin.getId(),
                admin.getNome(),
                admin.getEmail(),
                admin.getTelefone()
            )
        ).toList();
    }
}