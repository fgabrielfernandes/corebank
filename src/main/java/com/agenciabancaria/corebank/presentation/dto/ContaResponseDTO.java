package com.agenciabancaria.corebank.presentation.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ContaResponseDTO {
    private Long id;
    private String numeroConta;
    private BigDecimal saldo;
    private BigDecimal limiteChequeEspecial;
    private BigDecimal saldoDisponivelTotal;
    private Long usuarioId;
}
