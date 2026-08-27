package com.agenciabancaria.corebank.presentation.controller;

import com.agenciabancaria.corebank.application.usecase.AutenticarUsuarioUseCase;
import com.agenciabancaria.corebank.presentation.dto.LoginRequestDTO;
import com.agenciabancaria.corebank.presentation.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        String token = autenticarUsuarioUseCase.executar(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(new LoginResponseDTO(token, "Bearer"));
    }
}
