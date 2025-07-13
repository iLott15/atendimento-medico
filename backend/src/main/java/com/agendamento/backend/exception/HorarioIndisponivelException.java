package com.agendamento.backend.exception;

public class HorarioIndisponivelException extends RuntimeException {
    public HorarioIndisponivelException(String mensagem) {
        super(mensagem);
    }
}