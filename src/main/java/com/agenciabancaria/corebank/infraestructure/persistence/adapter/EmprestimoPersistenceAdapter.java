package com.agenciabancaria.corebank.infraestructure.persistence.adapter;

import com.agenciabancaria.corebank.domain.model.Emprestimo;
import com.agenciabancaria.corebank.domain.repository.EmprestimoRepositoryPort;
import com.agenciabancaria.corebank.infraestructure.persistence.mapper.EmprestimoMapper;
import com.agenciabancaria.corebank.infraestructure.persistence.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmprestimoPersistenceAdapter implements EmprestimoRepositoryPort {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        var entity = emprestimoMapper.toEntity(emprestimo);
        var savedEntity = emprestimoRepository.save(entity);
        return emprestimoMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id){
        return emprestimoRepository.findById(id)
                .map(emprestimoMapper::toDomain);
    }

    @Override
    public List<Emprestimo> buscarPorContaId(Long contaId){
        return emprestimoRepository.findByContaId(contaId).stream()
                .map(emprestimoMapper::toDomain)
                .toList();
    }
}
