package com.project.proposta_app.repository;

import com.project.proposta_app.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Integer> {
    List<Proposta> findAllByIntegradaIsFalse();


}
