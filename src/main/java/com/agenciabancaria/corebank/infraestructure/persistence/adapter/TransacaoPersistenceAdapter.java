package com.agenciabancaria.corebank.infraestructure.persistence.adapter;

import com.agenciabancaria.corebank.domain.model.Transacao;
import com.agenciabancaria.corebank.domain.repository.TransacaoRepositoryPort;
import com.agenciabancaria.corebank.infraestructure.persistence.entity.TransacaoEntity;
import com.agenciabancaria.corebank.infraestructure.persistence.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransacaoPersistenceAdapter implements  TransacaoRepositoryPort {

    private final TransacaoRepository transacaoRepository;

    @Override
    public Transacao salvar(Transacao transacao) {
        var entity = TransacaoEntity.fromDomain(transacao);
        var savedEntity = transacaoRepository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Transacao> buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .map(TransacaoEntity::toDomain);
    }

    @Override
    public List<Transacao> buscarPorContaOrigemIdOuContaDestinoId(Long contaOrigemId, Long contaDestinoId){
        return transacaoRepository.findByContaOrigemIdOrContaDestinoId(contaOrigemId, contaDestinoId).stream()
                .map(TransacaoEntity::toDomain)
                .toList();
    }

    @Override
    public List<Transacao> buscarExtratoPorConta(Long contaId){
        return transacaoRepository.buscarExtratoPorConta(contaId)
                .stream()
                .map(TransacaoEntity::toDomain)
                .toList();
    }
}
