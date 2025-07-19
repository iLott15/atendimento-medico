package com.agendamento.backend.dto;

import com.agendamento.backend.model.Usuario;

public record DadosUsuarioDTO(Long id, String login, String role) {
    public DadosUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getRole());
    }
}