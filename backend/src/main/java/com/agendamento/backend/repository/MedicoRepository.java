package com.agendamento.backend.repository;

import com.agendamento.backend.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface que fornece acesso ao banco (CRUD pronto)
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Aqui podemos depois adicionar buscas personalizadas, ex: findByNome, findByEspecialidade, etc.
}