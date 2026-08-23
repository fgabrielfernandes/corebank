package com.agenciabancaria.corebank.infraestructure.persistence.entity;

import com.agenciabancaria.corebank.enums.StatusConta;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_conta", indexes = {
        @Index(name = "idx_conta_numero", columnList = "numero_conta")
})
@EntityListeners(AbstractMethodError.class)
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

    @Column(name = "limite_global", nullable = false, precision = 19, scale = 2)
    private BigDecimal limiteGlobal;

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
}
