package com.agendamento.backend.dto;

import com.agendamento.backend.model.Paciente;
import lombok.Data;

@Data
public class PacienteResponseDTO {
    private Long id;
    private String nome;
    private String email;

    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
    }
}