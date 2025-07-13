package com.agendamento.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin")
@Data // Gera getters/setters, equals, hashCode, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;
}