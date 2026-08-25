package com.agenciabancaria.corebank.domain.model;
import com.agenciabancaria.corebank.domain.enums.StatusEmprestimo;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Emprestimo {

    private Long id;
    private BigDecimal valorSolicitado;
    private BigDecimal taxaJurosMensal;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcela;
    private BigDecimal valorTotalComJuros;
    private StatusEmprestimo status;
    private Conta conta;
    private LocalDate dataCriacao;
    private LocalDateTime dataAtualizacao;
}
