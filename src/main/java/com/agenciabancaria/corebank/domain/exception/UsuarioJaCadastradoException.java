package com.agenciabancaria.corebank.domain.exception;

public class UsuarioJaCadastradoException extends RegraDeNegocioException{
    public UsuarioJaCadastradoException(String cpf){
        super("Já existe um usuário cadastrado com o CPF: " +cpf);
    }
}
