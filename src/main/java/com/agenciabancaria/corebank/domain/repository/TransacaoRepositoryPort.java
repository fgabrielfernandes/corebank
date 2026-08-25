package com.agenciabancaria.corebank.domain.repository;

import com.agenciabancaria.corebank.domain.model.Transacao;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepositoryPort {
    Transacao salvar(Transacao transacao);
    Optional<Transacao> buscarPorId(Long id);
    List<Transacao> buscarPorContaOrigemIdOuContaDestinoId(Long contaOrigemId, Long contaDestinoId);
}
