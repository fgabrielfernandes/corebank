package com.agenciabancaria.corebank.infraestructure.persistence.mapper;

import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.infraestructure.persistence.entity.ContaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaMapper {

    private final UsuarioMapper usuarioMapper;

    public Conta toDomain(ContaEntity entity){
        if(entity == null){
            return null;
        }

        return Conta.builder()
                .id(entity.getId())
                .numeroConta(entity.getNumeroConta())
                .agencia(entity.getAgencia())
                .saldo(entity.getSaldo())
                .status(entity.getStatusConta())
                .usuario(usuarioMapper.toDomain(entity.getUsuario()))
                .dataCriacao(entity.getDataCriacao())
                .dataAtualizacao(entity.getDataAtualizacao())
                .build();
    }

    public ContaEntity toEntity(Conta domain){
        if(domain == null){
            return null;
        }

        return ContaEntity.builder()
                .id(domain.getId())
                .numeroConta(domain.getNumeroConta())
                .agencia(domain.getAgencia())
                .saldo(domain.getSaldo())
                .statusConta(domain.getStatus())
                .usuario(usuarioMapper.toEntity(domain.getUsuario()))
                .dataCriacao(domain.getDataCriacao())
                .dataAtualizacao(domain.getDataAtualizacao())
                .build();
    }
}
