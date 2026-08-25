package com.agenciabancaria.corebank.infraestructure.persistence.adapter;

import com.agenciabancaria.corebank.domain.model.Transacao;
import com.agenciabancaria.corebank.domain.repository.TransacaoRepositoryPort;
import com.agenciabancaria.corebank.infraestructure.persistence.mapper.TransacaoMapper;
import com.agenciabancaria.corebank.infraestructure.persistence.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransacaoPersistenceAdapter implements  TransacaoRepositoryPort {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoMapper transacaoMapper;

    @Override
    public Transacao salvar(Transacao transacao) {
        var entity = transacaoMapper.toEntity(transacao);
        var savedEntity = transacaoRepository.save(entity);
        return transacaoMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Transacao> buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .map(transacaoMapper::toDomain);
    }

    @Override
    public List<Transacao> buscarPorContaOrigemIdOuContaDestinoId(Long contaOrigemId, Long contaDestinoId){
        return transacaoRepository.findByContaOrigemIdOrContaDestinoId(contaOrigemId, contaDestinoId).stream()
                .map(transacaoMapper::toDomain)
                .toList();
    }
}
