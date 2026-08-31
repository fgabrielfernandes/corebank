package com.agenciabancaria.corebank.presentation.exception;

import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.exception.SaldoInsuficienteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Map<String, Object>> handleRegraDeNegocioException(RegraDeNegocioException ex){
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Map<String, Object>> handleSaldoInsuficienteException(SaldoInsuficienteException ex){
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex){
       Map<String, String> erros = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(error ->
               erros.put(error.getField(), error.getDefaultMessage())
       );

       Map<String, Object> body = new HashMap<>();
       body.put("timestamp", LocalDateTime.now());
       body.put("status", HttpStatus.BAD_REQUEST.value());
       body.put("error", "Erro de Validação");
       body.put("campos", erros);

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    private ResponseEntity<Map<String, Object>> construirResposta(HttpStatus status, String messagem){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("messagem", messagem);
        return ResponseEntity.status(status).body(body);
    }
}
