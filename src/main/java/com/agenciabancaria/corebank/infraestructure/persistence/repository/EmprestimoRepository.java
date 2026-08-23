package com.agenciabancaria.corebank.infraestructure.persistence.repository;

import com.agenciabancaria.corebank.infraestructure.persistence.entity.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {

    List<EmprestimoEntity> findByContaId(Long contaId);
}
