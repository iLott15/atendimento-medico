package com.agendamento.backend.dto;

import com.agendamento.backend.model.Medico;
import lombok.Data;

@Data
public class MedicoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String especialidade;

    public MedicoResponseDTO() {
    }

    public MedicoResponseDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
    }
}