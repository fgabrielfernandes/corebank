package com.agenciabancaria.corebank.infraestructure.persistence.repository;

import com.agenciabancaria.corebank.infraestructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByCpf(String cpf);
    Optional<UsuarioEntity> findByEmail(String email);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
