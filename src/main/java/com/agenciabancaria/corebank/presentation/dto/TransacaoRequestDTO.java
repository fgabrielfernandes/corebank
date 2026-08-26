package com.agenciabancaria.corebank.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequestDTO {

    @NotNull(message = "A conta de origem é obrigatória")
    private Long contaOrigemId;

    @NotNull(message = "A conta de destino é obrigatória")
    private Long contaDestinoId;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;
}
