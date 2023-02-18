package com.caixa.caixa.modulos.usuario.service;

import com.caixa.caixa.modulos.comum.exception.NotFoundException;
import com.caixa.caixa.modulos.usuario.dto.UsuarioRequest;
import com.caixa.caixa.modulos.usuario.dto.UsuarioResponse;
import com.caixa.caixa.modulos.usuario.model.Usuario;
import com.caixa.caixa.modulos.usuario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
// @Slf4j
public class UsuarioService {

    private static final NotFoundException ERR_USUARIO_NOT_FOUND = new NotFoundException("Usuário não encontrado.");

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public UsuarioResponse save(UsuarioRequest request) {
        var usuario = repository.save(Usuario.builder()
                .usuario(request.getUsuario())
                .senha(request.getSenha())
                .nome(request.getNome())
                .build());

        return UsuarioResponse.of(usuario);
    }

    public UsuarioResponse findById(UUID id) throws NotFoundException {
        var usuario = validarUsuarioExistente(id);
        return UsuarioResponse.of(usuario);
    }

    private Usuario validarUsuarioExistente(UUID id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> ERR_USUARIO_NOT_FOUND);
    }

}
