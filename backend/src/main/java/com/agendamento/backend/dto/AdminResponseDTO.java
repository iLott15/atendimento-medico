package com.agendamento.backend.dto;

public record AdminResponseDTO(
    Long id,
    String nome,
    String email,
    String telefone
) {}