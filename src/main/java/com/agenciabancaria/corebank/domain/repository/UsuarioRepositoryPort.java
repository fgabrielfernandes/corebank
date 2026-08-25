package com.agenciabancaria.corebank.domain.repository;

import com.agenciabancaria.corebank.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorCpf(String cpf);
    boolean existePorCpf(String cpf);
}
