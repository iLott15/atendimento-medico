package com.agendamento.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF (ok para APIs)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite qualquer requisição sem autenticação
            );
        return http.build();
    }
}