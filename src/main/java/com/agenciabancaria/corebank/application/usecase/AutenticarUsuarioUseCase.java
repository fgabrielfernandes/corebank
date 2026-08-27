package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.exception.RegraDeNegocioException;
import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.domain.repository.UsuarioRepositoryPort;
import com.agenciabancaria.corebank.domain.port.TokenPort;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenPort tokenPort;

    public String executar(String email, String senhaPura) {
        Usuario usuario = usuarioRepositoryPort.buscarPorEmail(email)
                .orElseThrow(() -> new RegraDeNegocioException("E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(senhaPura, usuario.getSenha())) {
            throw new RegraDeNegocioException("E-mail ou senha inválidos");
        }

        return tokenPort.gerarToken(usuario.getEmail());
    }
}
