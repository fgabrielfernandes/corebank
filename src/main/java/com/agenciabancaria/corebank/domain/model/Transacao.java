package com.agenciabancaria.corebank.domain.model;
import com.agenciabancaria.corebank.enums.TipoTransacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transacao {

    private Long id;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private String descricao;
    private Conta contaOrigem;
    private Conta contaDestino;
    private LocalDateTime dataTransacao;
}
