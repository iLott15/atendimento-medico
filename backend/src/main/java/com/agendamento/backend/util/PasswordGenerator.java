package com.agendamento.backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode("123456");
        System.out.println(senhaCriptografada);
    }
}