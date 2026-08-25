package com.agenciabancaria.corebank.domain.repository;

import com.agenciabancaria.corebank.domain.model.Emprestimo;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepositoryPort {
    Emprestimo salvar(Emprestimo emprestimo);
    Optional<Emprestimo> buscarPorId(Long id);
    List<Emprestimo> buscarPorContaId(Long contaId);
}
