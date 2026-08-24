package com.agenciabancaria.corebank.infraestructure.persistence.mapper;

import com.agenciabancaria.corebank.domain.model.Emprestimo;
import com.agenciabancaria.corebank.infraestructure.persistence.entity.EmprestimoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmprestimoMapper {

    private final ContaMapper contaMapper;

    public Emprestimo toDomain(EmprestimoEntity entity){
        if(entity == null){
            return null;
        }

        return Emprestimo.builder()
                .id(entity.getId())
                .valorSolicitado(entity.getValorSolicitado())
                .taxaJurosMensal(entity.getTaxaJurosMensal())
                .quantidadeParcelas(entity.getQuantidadeParcelas())
                .valorParcela(entity.getValorParcela())
                .valorTotalComJuros(entity.getValorTotalComJuros())
                .status(entity.getStatus())
                .conta(contaMapper.toDomain(entity.getConta()))
                .dataCriacao(entity.getDataCriacao())
                .dataAtualizacao(entity.getDataAtualizacao())
                .build();
    }

    public EmprestimoEntity toEntity(Emprestimo domain){
        if(domain == null){
            return null;
        }

        return EmprestimoEntity.builder()
                .id(domain.getId())
                .valorSolicitado(domain.getValorSolicitado())
                .taxaJurosMensal(domain.getTaxaJurosMensal())
                .quantidadeParcelas(domain.getQuantidadeParcelas())
                .valorParcela(domain.getValorParcela())
                .valorTotalComJuros(domain.getValorTotalComJuros())
                .status(domain.getStatus())
                .conta(contaMapper.toEntity(domain.getConta()))
                .dataCriacao(domain.getDataCriacao())
                .dataAtualizacao(domain.getDataAtualizacao())
                .build();
    }
}
