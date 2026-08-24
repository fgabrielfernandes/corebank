package com.agenciabancaria.corebank.domain.model;
import com.agenciabancaria.corebank.enums.StatusConta;
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
    private StatusConta status;
    private Usuario usuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
