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

    private Long idMedico;
    private Long idPaciente;

    public AgendamentoResponseDTO(Agendamento a) {
        this.id = a.getId();
        this.medicoNome = a.getMedico().getNome();
        this.idMedico = a.getMedico().getId();
        this.pacienteNome = a.getPaciente().getNome();
        this.idPaciente = a.getPaciente().getId();
        this.data = a.getData();
        this.motivo = a.getMotivo();
        this.descricao = a.getDescricao();
    }

    // Getters
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

    public Long getIdMedico() {
        return idMedico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }
}