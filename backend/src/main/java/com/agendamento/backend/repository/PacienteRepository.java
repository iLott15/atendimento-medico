package com.agendamento.backend.repository;

import com.agendamento.backend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Nada a declarar — o JPA já cuida do CRUD
}