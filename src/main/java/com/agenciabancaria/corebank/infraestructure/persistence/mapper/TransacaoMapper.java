package com.agenciabancaria.corebank.infraestructure.persistence.mapper;

import com.agenciabancaria.corebank.domain.model.Transacao;
import com.agenciabancaria.corebank.infraestructure.persistence.entity.TransacaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransacaoMapper {

    private final ContaMapper contaMapper;

    public Transacao toDomain(TransacaoEntity entity) {
        if (entity == null){
            return null;
        }

        return Transacao.builder()
                .id(entity.getId())
                .valor(entity.getValor())
                .tipo(entity.getTipoTransacao())
                .descricao(entity.getDescricao())
                .contaOrigem(contaMapper.toDomain(entity.getContaOrigem()))
                .contaDestino(contaMapper.toDomain(entity.getContaDestino()))
                .dataTransacao(entity.getDataTransacao())
                .build();
    }

    public TransacaoEntity toEntity(Transacao domain) {
        if (domain == null){
            return null;
        }

        return TransacaoEntity.builder()
                .id(domain.getId())
                .valor(domain.getValor())
                .tipoTransacao(domain.getTipo())
                .descricao(domain.getDescricao())
                .contaOrigem(contaMapper.toEntity(domain.getContaOrigem()))
                .contaDestino(contaMapper.toEntity(domain.getContaDestino()))
                .dataTransacao(domain.getDataTransacao())
                .build();
    }
}
