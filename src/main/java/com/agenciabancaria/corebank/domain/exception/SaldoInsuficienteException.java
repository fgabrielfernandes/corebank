package com.agenciabancaria.corebank.domain.exception;

public class SaldoInsuficienteException extends RegraDeNegocioException{
    public SaldoInsuficienteException(){
        super("Saldo insuficiente para realizar a transação.");
    }
}
