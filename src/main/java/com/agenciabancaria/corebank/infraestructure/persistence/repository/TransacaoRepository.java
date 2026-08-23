package com.agenciabancaria.corebank.infraestructure.persistence.repository;

import com.agenciabancaria.corebank.infraestructure.persistence.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

    @Query("SELECT t FROM TransacaoEntity t WHERE t.contaOrigem.id = :contaId ORDER BY t.dataTransacao DESC")
    List<TransacaoEntity> buscarExtratoPorConta(@Param("contaId")  Long contaId);

}
