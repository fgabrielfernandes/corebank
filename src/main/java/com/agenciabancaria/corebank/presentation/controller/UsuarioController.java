package com.agenciabancaria.corebank.presentation.controller;

import com.agenciabancaria.corebank.application.usecase.CriarUsuarioUseCase;
import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.presentation.dto.UsuarioRequestDTO;
import com.agenciabancaria.corebank.presentation.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO request){
        Usuario usuarioDomain = Usuario.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .build();

        Usuario usuarioCriado = criarUsuarioUseCase.executar(usuarioDomain);

        UsuarioResponseDTO response = UsuarioResponseDTO.builder()
                .id(usuarioCriado.getId())
                .nome(usuarioCriado.getNome())
                .cpf(usuarioCriado.getCpf())
                .email(usuarioCriado.getEmail())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
