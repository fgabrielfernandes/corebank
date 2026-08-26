package com.agenciabancaria.corebank.presentation.controller;

import com.agenciabancaria.corebank.application.usecase.RealizarTransacaoUseCase;
import com.agenciabancaria.corebank.domain.model.Transacao;
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

    private final RealizarTransacaoUseCase realizarTransacaoUseCase;

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> realizar(@RequestBody @Valid TransacaoRequestDTO request) {
        Transacao transacao = realizarTransacaoUseCase.executar(
                request.getContaOrigemId(),
                request.getContaDestinoId(),
                request.getValor()
        );

        TransacaoResponseDTO response = TransacaoResponseDTO.builder()
                .id(transacao.getId())
                .contaOrigemId(transacao.getContaOrigem().getId())
                .contaDestinoId(transacao.getContaDestino().getId())
                .valor(transacao.getValor())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
