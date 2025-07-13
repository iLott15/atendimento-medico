package com.agendamento.backend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @NotNull Long idMedico,
        @NotNull Long idPaciente,
        @NotNull @Future LocalDateTime data,
        String motivo,
        String descricao) {
}