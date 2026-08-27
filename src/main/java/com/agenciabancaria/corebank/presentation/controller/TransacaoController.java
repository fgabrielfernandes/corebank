package com.agenciabancaria.corebank.presentation.controller;

import com.agenciabancaria.corebank.application.usecase.DepositarUseCase;
import com.agenciabancaria.corebank.application.usecase.RealizarTransacaoUseCase;
import com.agenciabancaria.corebank.application.usecase.SacarUseCase;
import com.agenciabancaria.corebank.presentation.dto.TransacaoRequestDTO;
import com.agenciabancaria.corebank.presentation.dto.TransacaoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final DepositarUseCase depositarUseCase;
    private final SacarUseCase sacarUseCase;
    private final RealizarTransacaoUseCase realizarTransacaoUseCase;

    @PostMapping("/deposito")
    public ResponseEntity<TransacaoResponseDTO> depositar(@RequestBody @Valid TransacaoRequestDTO request) {
        var transacao = depositarUseCase.executar(
                request.getContaDestinoId(),
                request.getValor(),
                request.getDescricao()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(TransacaoResponseDTO.fromDomain(transacao));
    }

    @PostMapping("/saque")
    public ResponseEntity<TransacaoResponseDTO> sacar(@RequestBody @Valid TransacaoRequestDTO request) {
        var transacao = sacarUseCase.executar(
                request.getContaOrigemId(),
                request.getValor(),
                request.getDescricao()
        );
        return ResponseEntity.ok(TransacaoResponseDTO.fromDomain(transacao));
    }

    @PostMapping("/transferencia")
    public ResponseEntity<TransacaoResponseDTO> transferir(@RequestBody @Valid TransacaoRequestDTO request) {
        var transacao = realizarTransacaoUseCase.executar(
                request.getContaOrigemId(),
                request.getContaDestinoId(),
                request.getValor(),
                request.getDescricao()
        );
        return ResponseEntity.ok(TransacaoResponseDTO.fromDomain(transacao));
    }
}
