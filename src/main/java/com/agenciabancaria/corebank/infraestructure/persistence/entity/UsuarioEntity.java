package com.agenciabancaria.corebank.infraestructure.persistence.entity;

import com.agenciabancaria.corebank.enums.TipoPerfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuario", indexes = {
        @Index(name = "idx_usuario_cpf", columnList = "cpf"),
        @Index(name = "idx_usuario_email", columnList = "email")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @Column(name= "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name ="senha_hash", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_perfil", nullable = false, length = 30)
    private TipoPerfil tipoPerfil;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
