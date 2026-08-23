package com.agenciabancaria.corebank.infraestructure.persistence.repository;

import com.agenciabancaria.corebank.infraestructure.persistence.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    Optional<ContaEntity> findByNumeroConta(String numeroConta);

    List<ContaEntity> findByUsuarioId(Long usuarioId);

    boolean existsByNumeroConta(String numeroConta);
}
