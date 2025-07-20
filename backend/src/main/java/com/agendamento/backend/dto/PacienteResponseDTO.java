package com.agendamento.backend.dto;

import com.agendamento.backend.model.Paciente;
import com.agendamento.backend.dto.EnderecoDTO;
import lombok.Data;

@Data
public class PacienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private EnderecoDTO endereco;

    public PacienteResponseDTO() {
    }

    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        if (paciente.getEndereco() != null) {
            this.endereco = new EnderecoDTO(paciente.getEndereco());
        }
    }
}