package com.agenciabancaria.corebank.infraestructure.persistence.entity;

import com.agenciabancaria.corebank.domain.enums.StatusConta;
import com.agenciabancaria.corebank.domain.model.Conta;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_conta", indexes = {
        @Index(name = "idx_conta_numero", columnList = "numero_conta")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "agencia", nullable = false, length = 4)
    private String agencia;

    @Column(name = "numero_conta", nullable = false, unique = true, length = 10)
    private String numeroConta;

    @Column(name = "saldo", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Column(name = "limite_cheque_especial", nullable = false, precision = 19, scale = 2)
    private BigDecimal limiteChequeEspecial;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_conta", nullable = false, length = 30)
    private StatusConta statusConta;

    @Column(name = "tentativas_senha_invalidas", nullable = false)
    private Integer tentativasSenha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Conta toDomain() {
        return Conta.builder()
                .id(this.id)
                .agencia(this.agencia)
                .numeroConta(this.numeroConta)
                .saldo(this.saldo)
                .limiteChequeEspecial(this.limiteChequeEspecial)
                .status(this.statusConta)
                .usuario(this.usuario != null ? this.usuario.toDomain() : null)
                .dataCriacao(this.dataCriacao)
                .dataAtualizacao(this.dataAtualizacao)
                .build();
    }

    public static ContaEntity fromDomain(Conta conta) {
        if(conta == null) return null;
        return ContaEntity.builder()
                .id(conta.getId())
                .agencia(conta.getAgencia())
                .numeroConta(conta.getNumeroConta())
                .saldo(conta.getSaldo())
                .limiteChequeEspecial(conta.getLimiteChequeEspecial())
                .statusConta(conta.getStatus())
                .usuario(conta.getUsuario() != null ? UsuarioEntity.fromDomain(conta.getUsuario()) : null)
                .build();
    }
}
