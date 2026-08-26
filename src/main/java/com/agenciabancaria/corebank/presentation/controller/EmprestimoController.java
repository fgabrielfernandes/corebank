package com.agenciabancaria.corebank.presentation.controller;

import com.agenciabancaria.corebank.application.usecase.SolicitarEmprestimoUseCase;
import com.agenciabancaria.corebank.domain.model.Emprestimo;
import com.agenciabancaria.corebank.presentation.dto.EmprestimoRequestDTO;
import com.agenciabancaria.corebank.presentation.dto.EmprestimoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final SolicitarEmprestimoUseCase solicitarEmprestimoUseCase;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> solicitar(@RequestBody @Valid EmprestimoRequestDTO request){
        Emprestimo emprestimo = solicitarEmprestimoUseCase.executar(
                request.getContaId(),
                request.getValorSolicitado(),
                request.getNumeroParcelas()
        );

        EmprestimoResponseDTO response = EmprestimoResponseDTO.builder()
                .id(emprestimo.getId())
                .contaId(emprestimo.getConta().getId())
                .valorSolicitado(emprestimo.getValorSolicitado())
                .valorTotalComJuros(emprestimo.getValorTotalComJuros())
                .valorParcela(emprestimo.getValorParcela())
                .status(emprestimo.getStatus())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
