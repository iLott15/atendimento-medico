package com.agendamento.backend.dto;

import com.agendamento.backend.model.Endereco;
import com.agendamento.backend.model.Medico;

public class MedicoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String especialidade;
    private Endereco endereco; // <--- adicionar isso

    public MedicoResponseDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.endereco = medico.getEndereco(); // <--- e isso
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}