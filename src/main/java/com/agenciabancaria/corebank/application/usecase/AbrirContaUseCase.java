package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.model.Conta;
import com.agenciabancaria.corebank.domain.repository.ContaRepositoryPort;
import com.agenciabancaria.corebank.domain.repository.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbrirContaUseCase {

    private final ContaRepositoryPort contaRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public Conta executar(Conta conta) {
        if (conta.getUsuario() == null || conta.getUsuario().getId() == null) {
            throw new RegraDeNegocioException("É necessário informar um usuário válido para abrir uma conta.");
        }

        usuarioRepositoryPort.buscarPorId(conta.getUsuario().getId())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado para abertura de conta."));

        if (contaRepositoryPort.existePorNumeroConta(conta.getNumeroConta())) {
            throw new RegraDeNegocioException("Já existe uma conta cadastrada com o número: " +conta.getNumeroConta());
        }

        return contaRepositoryPort.salvar(conta);
    }
}
