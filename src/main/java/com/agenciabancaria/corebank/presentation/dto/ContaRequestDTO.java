package com.agenciabancaria.corebank.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaRequestDTO {

    @NotBlank(message = "O número da conta é obrigatório")
    private String numeroConta;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    private BigDecimal limiteChequeEspecial;
}
