package com.agenciabancaria.corebank.presentation.dto;

import com.agenciabancaria.corebank.domain.enums.TipoTransacao;
import com.agenciabancaria.corebank.domain.model.Transacao;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransacaoResponseDTO {
    private Long id;
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;
    private String descricao;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private LocalDateTime dataTransacao;

    public static TransacaoResponseDTO fromDomain(Transacao transacao) {
        if (transacao == null) return null;

        return TransacaoResponseDTO.builder()
                .id(transacao.getId())
                .tipoTransacao(transacao.getTipo())
                .valor(transacao.getValor())
                .descricao(transacao.getDescricao())
                .contaOrigemId(transacao.getContaOrigem() != null ? transacao.getContaOrigem().getId() : null)
                .contaDestinoId(transacao.getContaDestino() != null ? transacao.getContaDestino().getId() : null)
                .dataTransacao(transacao.getDataTransacao())
                .build();
    }
}
