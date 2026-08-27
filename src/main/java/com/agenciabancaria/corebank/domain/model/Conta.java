package com.agenciabancaria.corebank.domain.model;
import com.agenciabancaria.corebank.domain.enums.StatusConta;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {

    private Long id;
    private String numeroConta;
    private String agencia;
    private BigDecimal saldo;
    private BigDecimal limiteChequeEspecial; // <-- Adicionado
    private StatusConta status;
    private Usuario usuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private Integer tentativasSenha;
    private BigDecimal limiteGlobal;

    public BigDecimal getSaldoDisponivelTotal() { // <-- Adicionado
        BigDecimal limite =(limiteChequeEspecial != null) ? limiteChequeEspecial : BigDecimal.ZERO;
        BigDecimal saldoAtual = (saldo != null) ? saldo : BigDecimal.ZERO;
        return saldoAtual.add(limite);
    }
}
