package com.agendamento.backend.dto;

import com.agendamento.backend.model.Agendamento;

import java.time.LocalDateTime;

public class AgendamentoResponseDTO {

    private Long id;
    private String medicoNome;
    private String pacienteNome;
    private LocalDateTime data;
    private String motivo;
    private String descricao;

    public AgendamentoResponseDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.medicoNome = agendamento.getMedico().getNome();
        this.pacienteNome = agendamento.getPaciente().getNome();
        this.data = agendamento.getData();
        this.motivo = agendamento.getMotivo();
        this.descricao = agendamento.getDescricao();
    }

    public AgendamentoResponseDTO(Long id, LocalDateTime data, String descricao, String motivo, String medicoNome,
            String pacienteNome) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.motivo = motivo;
        this.medicoNome = medicoNome;
        this.pacienteNome = pacienteNome;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDescricao() {
        return descricao;
    }
}