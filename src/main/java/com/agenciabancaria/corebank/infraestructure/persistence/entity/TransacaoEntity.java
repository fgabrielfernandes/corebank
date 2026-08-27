package com.agenciabancaria.corebank.infraestructure.persistence.entity;

import com.agenciabancaria.corebank.domain.enums.TipoTransacao;
import com.agenciabancaria.corebank.domain.model.Transacao;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacao", indexes = {
        @Index(name = "idx_transacao_data", columnList = "data_transacao")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "valor", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao", nullable = false, length = 30)
    private TipoTransacao tipoTransacao;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_origem_id")
    private ContaEntity contaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_destino_id")
    private ContaEntity contaDestino;

    @CreatedDate
    @Column(name = "data_transacao", nullable = false, updatable = false)
    private LocalDateTime dataTransacao;

    public Transacao toDomain(){
        return Transacao.builder()
                .id(this.id)
                .valor(this.valor)
                .tipo(this.tipoTransacao)
                .descricao(this.descricao)
                .contaOrigem(this.contaOrigem != null ? this.contaOrigem.toDomain() : null)
                .contaDestino(this.contaDestino != null ? this.contaDestino.toDomain() :null)
                .dataTransacao(this.dataTransacao)
                .build();
    }

    public static TransacaoEntity fromDomain(Transacao transacao){
        if(transacao == null) return null;
        return TransacaoEntity.builder()
                .id(transacao.getId())
                .valor(transacao.getValor())
                .tipoTransacao(transacao.getTipo())
                .descricao(transacao.getDescricao())
                .contaOrigem(transacao.getContaOrigem() != null ? ContaEntity.fromDomain(transacao.getContaOrigem()) : null)
                .contaDestino(transacao.getContaDestino() != null ? ContaEntity.fromDomain(transacao.getContaDestino()):null)
                .dataTransacao(transacao.getDataTransacao())
                .build();
    }
}
