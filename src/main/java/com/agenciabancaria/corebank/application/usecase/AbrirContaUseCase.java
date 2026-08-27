package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.enums.StatusConta;
import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.domain.repository.ContaRepositoryPort;
import com.agenciabancaria.corebank.domain.repository.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AbrirContaUseCase {

    private final ContaRepositoryPort contaRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public Conta executar(Conta conta) {
        if (conta.getUsuario() == null || conta.getUsuario().getId() == null) {
            throw new RegraDeNegocioException("É necessário informar um usuário válido para abrir uma conta.");
        }

        //1. Injeta o objeto Usuario completo vindo do banco
        Usuario usuario = usuarioRepositoryPort.buscarPorId(conta.getUsuario().getId())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado para abertura de conta."));

        if (contaRepositoryPort.existePorNumeroConta(conta.getNumeroConta())){
            throw new RegraDeNegocioException("Já existe uma conta cadastrada com o número: " + conta.getNumeroConta());
        }

        // 2. Garante o preenchimento das regras e campos obrigatórios do domínio
        if(conta.getAgencia() == null || conta.getAgencia().isBlank()){
            conta.setAgencia("0001");
        }

        if (conta.getSaldo() == null){
            conta.setSaldo(BigDecimal.ZERO);
        }

        if(conta.getLimiteChequeEspecial() == null){
            conta.setLimiteChequeEspecial(BigDecimal.ZERO);
        }

        if(conta.getStatus() == null){
            conta.setStatus(StatusConta.ATIVA);
        }

        if(conta.getTentativasSenha() == null){
            conta.setTentativasSenha(0);
        }

        if(conta.getLimiteGlobal() == null){
            conta.setLimiteGlobal(BigDecimal.ZERO);
        }

        conta.setUsuario(usuario);

        return contaRepositoryPort.salvar(conta);
    }
}
