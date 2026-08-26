package com.agenciabancaria.corebank.presentation.dto;

import com.agenciabancaria.corebank.domain.enums.StatusEmprestimo;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EmprestimoResponseDTO {
    private Long id;
    private Long contaId;
    private BigDecimal valorSolicitado;
    private BigDecimal valorTotalComJuros;
    private BigDecimal valorParcela;
    private StatusEmprestimo status;
}
