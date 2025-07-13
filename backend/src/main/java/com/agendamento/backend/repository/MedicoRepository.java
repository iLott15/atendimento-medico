package com.agendamento.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agendamento.backend.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Medico> findByEspecialidadeIgnoreCase(String especialidade, Pageable pageable);
}