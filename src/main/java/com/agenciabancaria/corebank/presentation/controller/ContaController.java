package com.agenciabancaria.corebank.presentation.controller;

import com.agenciabancaria.corebank.application.usecase.AbrirContaUseCase;
import com.agenciabancaria.corebank.domain.enums.StatusConta;
import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.presentation.dto.ContaRequestDTO;
import com.agenciabancaria.corebank.presentation.dto.ContaResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaController {

    private final AbrirContaUseCase abrirContaUseCase;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> abrir(@RequestBody @Valid ContaRequestDTO request) {
        Conta contaDomain = Conta.builder()
                .numeroConta(request.getNumeroConta())
                .saldo(BigDecimal.ZERO)
                .limiteChequeEspecial(request.getLimiteChequeEspecial() != null ? request.getLimiteChequeEspecial() : BigDecimal.ZERO)
                .status(StatusConta.ATIVA)
                .usuario(Usuario.builder().id(request.getUsuarioId()).build())
                .build();

        Conta contaCriada = abrirContaUseCase.executar(contaDomain);

        ContaResponseDTO response = ContaResponseDTO.builder()
                .id(contaCriada.getId())
                .numeroConta(contaCriada.getNumeroConta())
                .saldo(contaCriada.getSaldo())
                .limiteChequeEspecial(contaCriada.getLimiteChequeEspecial())
                .saldoDisponivelTotal(contaCriada.getSaldoDisponivelTotal())
                .usuarioId(contaCriada.getUsuario().getId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
