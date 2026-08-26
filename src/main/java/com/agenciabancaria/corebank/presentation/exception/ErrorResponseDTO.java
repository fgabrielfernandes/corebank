package com.agenciabancaria.corebank.presentation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private String mensagem;
    private int status;
    private LocalDateTime timestamp;
}
