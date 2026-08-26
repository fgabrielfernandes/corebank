package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.exception.SaldoInsuficienteException;
import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.domain.model.Transacao;
import com.agenciabancaria.corebank.domain.repository.ContaRepositoryPort;
import com.agenciabancaria.corebank.domain.repository.TransacaoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RealizarTransacaoUseCase {

    private final TransacaoRepositoryPort transacaoRepositoryPort;
    private final ContaRepositoryPort contaRepositoryPort;

    @Transactional
    public Transacao executar(Long contaOrigemId, Long contaDestinoId, BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new RegraDeNegocioException("O valor da transação deve ser maior que zero");
        }

        if(contaOrigemId.equals(contaDestinoId)){
            throw new RegraDeNegocioException("A conta de origem e destino não podem ser iguais.");
        }

        Conta origem = contaRepositoryPort.buscarPorId(contaOrigemId)
                .orElseThrow(()-> new RegraDeNegocioException("Conta de origem não encontrada"));

        Conta destino = contaRepositoryPort.buscarPorId(contaDestinoId)
                .orElseThrow(() -> new RegraDeNegocioException("Conta de destino não encontrada"));

        if(origem.getSaldoDisponivelTotal().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException();
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        contaRepositoryPort.salvar(origem);
        contaRepositoryPort.salvar(destino);

        Transacao transacao = Transacao.builder()
                .contaOrigem(origem)
                .contaDestino(destino)
                .valor(valor)
                .build();

        return transacaoRepositoryPort.salvar(transacao);
    }
}
