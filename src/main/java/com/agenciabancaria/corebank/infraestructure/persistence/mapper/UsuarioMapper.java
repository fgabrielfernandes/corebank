package com.agenciabancaria.corebank.infraestructure.persistence.mapper;

import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.infraestructure.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toDomain(UsuarioEntity entity){
        if(entity == null){
            return null;
        }

        return Usuario.builder()
                .id(entity.getId())
                .nome(entity.getNomeCompleto())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .perfil(entity.getTipoPerfil())
                .dataCriacao(entity.getDataCriacao())
                .dataAtualizacao(entity.getDataAtualizacao())
                .build();
    }

    public UsuarioEntity toEntity(Usuario domain){
        if(domain == null){
            return null;
        }

        return UsuarioEntity.builder()
                .id(domain.getId())
                .nomeCompleto(domain.getNome())
                .cpf(domain.getCpf())
                .email(domain.getEmail())
                .senha(domain.getSenha())
                .tipoPerfil(domain.getPerfil())
                .dataCriacao(domain.getDataCriacao())
                .dataAtualizacao(domain.getDataAtualizacao())
                .build();
    }
}
