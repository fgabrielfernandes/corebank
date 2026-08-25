package com.agenciabancaria.corebank.domain.repository;

import com.agenciabancaria.corebank.domain.model.Conta;

import java.util.Optional;

public interface ContaRepositoryPort {
    Conta salvar(Conta conta);
    Optional<Conta> buscarPorId(Long id);
    Optional<Conta> buscarPorNumeroConta(String numeroConta);
    boolean existePorNumeroConta(String numeroConta);
}
