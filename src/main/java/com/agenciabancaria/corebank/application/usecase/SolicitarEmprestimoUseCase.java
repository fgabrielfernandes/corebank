package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.enums.StatusEmprestimo;
import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.domain.model.Emprestimo;
import com.agenciabancaria.corebank.domain.repository.ContaRepositoryPort;
import com.agenciabancaria.corebank.domain.repository.EmprestimoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class SolicitarEmprestimoUseCase {

    private static final BigDecimal TAXA_JUROS_FIXA = new BigDecimal("0.05"); //5% de taxa fixa

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;
    private final ContaRepositoryPort contaRepositoryPort;

    @Transactional
    public Emprestimo executar(Long contaId, BigDecimal valorSolicitado, Integer numeroParcelas){
        if(valorSolicitado.compareTo(BigDecimal.ZERO) <= 0){
            throw new RegraDeNegocioException("O valor do empréstimo deve ser maior que zero");
        }

        if (numeroParcelas == null || numeroParcelas <= 0){
            throw new RegraDeNegocioException("O numero de parcelas deve ser maior que zero");
        }

        Conta conta = contaRepositoryPort.buscarPorId(contaId)
                .orElseThrow(()-> new RegraDeNegocioException("Conta não encontrada para solicitação de empréstimo."));

        // Cálculos financeiros
        BigDecimal valorJuros = valorSolicitado.multiply(TAXA_JUROS_FIXA);
        BigDecimal valorTotalComJuros = valorSolicitado.add(valorJuros);
        BigDecimal valorParcela = valorTotalComJuros.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);

        // Atualização do saldo
        conta.setSaldo(conta.getSaldo().add(valorSolicitado));
        contaRepositoryPort.salvar(conta);

        // Construção do domínio completo
        Emprestimo emprestimo = Emprestimo.builder()
                .conta(conta)
                .valorSolicitado(valorSolicitado)
                .valorTotalComJuros(valorTotalComJuros)
                .valorParcela(valorParcela)
                .status(StatusEmprestimo.APROVADO)
                .build();
        return emprestimoRepositoryPort.salvar(emprestimo);
    }
}
