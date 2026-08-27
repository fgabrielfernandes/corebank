package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.enums.StatusConta;
import com.agenciabancaria.corebank.domain.enums.TipoTransacao;
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
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SacarUseCase {

    private final ContaRepositoryPort contaRepositoryPort;
    private final TransacaoRepositoryPort transacaoRepositoryPort;

    @Transactional
    public Transacao executar(Long contaId, BigDecimal valor, String descricao){
        if(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new RegraDeNegocioException("O valor do saque deve ser maior que zero");
        }

        Conta conta = contaRepositoryPort.buscarPorId(contaId)
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada."));

        if(conta.getStatus() != StatusConta.ATIVA){
            throw new RegraDeNegocioException("A conta informada não está ativa para saques.");
        }

        if(conta.getSaldoDisponivelTotal().compareTo(valor) < 0){
            throw new SaldoInsuficienteException();
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepositoryPort.salvar(conta);

        Transacao transacao = Transacao.builder()
                .tipo(TipoTransacao.SAQUE)
                .valor(valor)
                .descricao(descricao != null && !descricao.isBlank() ? descricao : "Saque em conta")
                .contaOrigem(conta)
                .dataTransacao(LocalDateTime.now())
                .build();

        return transacaoRepositoryPort.salvar(transacao);
    }
}
