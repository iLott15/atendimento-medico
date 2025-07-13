package com.agendamento.backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode("");
        System.out.println(senhaCriptografada);
    }
}