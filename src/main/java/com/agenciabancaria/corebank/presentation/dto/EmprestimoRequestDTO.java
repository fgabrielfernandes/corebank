package com.agenciabancaria.corebank.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmprestimoRequestDTO {

    @NotNull(message = "O ID da conta é obrigatório")
    private Long contaId;

    @NotNull(message = "O valor solicitado é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valorSolicitado;

    @NotNull(message = "O número de parcelas é obrigatório")
    @Min(value = 1, message = "O número de parcelas deve ser pelo menos 1")
    private Integer numeroParcelas;
}
