package com.agenciabancaria.corebank.infraestructure.persistence.adapter;

import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.domain.repository.ContaRepositoryPort;
import com.agenciabancaria.corebank.infraestructure.persistence.mapper.ContaMapper;
import com.agenciabancaria.corebank.infraestructure.persistence.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContaPersistenceAdapter implements ContaRepositoryPort {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    @Override
    public Conta salvar(Conta conta) {
        var entity = contaMapper.toEntity(conta);
        var savedEntity = contaRepository.save(entity);
        return contaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Conta> buscarPorId(Long id) {
        return contaRepository.findById(id)
                .map(contaMapper::toDomain);
    }

    @Override
    public Optional<Conta> buscarPorNumeroConta(String numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .map(contaMapper::toDomain);
    }

    @Override
    public boolean existePorNumeroConta(String numeroConta) {
        return contaRepository.existsByNumeroConta(numeroConta);
    }
}
