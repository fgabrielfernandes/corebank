package com.agenciabancaria.corebank.domain.repository;

import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.infraestructure.persistence.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorCpf(String cpf);
    Optional<Usuario> buscarPorEmail(String email);
    boolean existePorCpf(String cpf);
    boolean existePorEmail(String email);
}
