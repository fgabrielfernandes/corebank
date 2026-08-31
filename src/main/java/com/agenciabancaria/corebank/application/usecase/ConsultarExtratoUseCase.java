package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.model.Transacao;
import com.agenciabancaria.corebank.domain.repository.ContaRepositoryPort;
import com.agenciabancaria.corebank.domain.repository.TransacaoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarExtratoUseCase {

    private final TransacaoRepositoryPort transacaoRepositoryPort;
    private final ContaRepositoryPort contaRepositoryPort;

    @Transactional(readOnly = true)
    public List<Transacao> executar(Long contaId) {
        contaRepositoryPort.buscarPorId(contaId)
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada."));

        return transacaoRepositoryPort.buscarExtratoPorConta(contaId);
    }
}
