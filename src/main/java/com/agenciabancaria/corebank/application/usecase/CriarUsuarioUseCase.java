package com.agenciabancaria.corebank.application.usecase;

import com.agenciabancaria.corebank.domain.exception.UsuarioJaCadastradoException;
import com.agenciabancaria.corebank.domain.model.Usuario;
import com.agenciabancaria.corebank.domain.repository.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public Usuario executar(Usuario usuario){
        if(usuarioRepositoryPort.existePorCpf(usuario.getCpf())){
            throw new UsuarioJaCadastradoException(usuario.getCpf());
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepositoryPort.salvar(usuario);
    }
}
