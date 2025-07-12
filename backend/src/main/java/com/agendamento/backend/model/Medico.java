package com.agendamento.backend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "CRM é obrigatório")
    @Size(min = 4, max = 10, message = "CRM deve ter entre 4 e 10 caracteres")
    private String crm;

    @NotBlank(message = "Especialidade é obrigatória")
    private String especialidade;

    @Valid
    @Embedded
    private Endereco endereco;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}