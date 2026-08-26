package com.agenciabancaria.corebank.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransacaoResponseDTO {
    private Long id;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private BigDecimal valor;
}
