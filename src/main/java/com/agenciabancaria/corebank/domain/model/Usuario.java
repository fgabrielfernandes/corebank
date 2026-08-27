package com.agenciabancaria.corebank.domain.model;
import com.agenciabancaria.corebank.domain.enums.TipoPerfil;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private TipoPerfil tipoPerfil;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
