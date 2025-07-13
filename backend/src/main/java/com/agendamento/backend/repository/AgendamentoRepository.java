package com.agendamento.backend.repository;

import com.agendamento.backend.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByMedicoId(Long medicoId);

    @Query("SELECT a FROM Agendamento a " +
            "WHERE (:medicoId IS NULL OR a.medico.id = :medicoId) " +
            "AND (:pacienteId IS NULL OR a.paciente.id = :pacienteId) " +
            "AND (:data IS NULL OR a.data = :data)")
    List<Agendamento> findByFiltros(@Param("medicoId") Long medicoId,
            @Param("pacienteId") Long pacienteId,
            @Param("data") LocalDate data);

    boolean existsByMedicoIdAndData(Long medicoId, java.time.LocalDateTime data);

    void deleteAllByIdIn(List<Long> ids);
}