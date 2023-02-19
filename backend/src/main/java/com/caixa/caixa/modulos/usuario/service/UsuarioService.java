package com.caixa.caixa.modulos.usuario.service;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.comum.exception.NotFoundException;
import com.caixa.caixa.modulos.usuario.dto.UsuarioRequest;
import com.caixa.caixa.modulos.usuario.dto.UsuarioResponse;
import com.caixa.caixa.modulos.usuario.model.Usuario;
import com.caixa.caixa.modulos.usuario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
// @Slf4j
public class UsuarioService {

    private static final NotFoundException ERR_USUARIO_NOT_FOUND = new NotFoundException("Usuário não encontrado.");
    private static final NotFoundException ERR_USUARIO_EM_USO = new NotFoundException(
            "Usuário já está sendo utilizado.");
    private static final NotFoundException ERR_USUARIO_OU_SENHA_INCORRETO = new NotFoundException(
            "Usuário ou senha incorreto.");

    @Autowired
    private UsuarioRepository repository;

    private Usuario validarUsuarioExistente(UUID id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> ERR_USUARIO_NOT_FOUND);
    }

    private Usuario validarUsuarioSenha(String usuario, String senha) throws NotFoundException {
        return repository.findByUsuarioAndSenha(usuario, senha)
                .orElseThrow(() -> ERR_USUARIO_OU_SENHA_INCORRETO);
    }

    public Page<UsuarioResponse> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(UsuarioResponse::of);
    }

    @Transactional
    public UsuarioResponse save(UsuarioRequest request) throws NotFoundException {
        var usuarioExistente = repository.findByUsuario(request.getUsuario());
        
        if (usuarioExistente.isPresent()) {
            throw ERR_USUARIO_EM_USO;
        }

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

    public UsuarioResponse findByUsuario(String usuarioParam) throws NotFoundException {
        var usuarioResponse = repository.findByUsuario(usuarioParam);

        if (usuarioResponse.isEmpty()) {
            throw ERR_USUARIO_NOT_FOUND;
        }

        var usuario = usuarioResponse.get();
        return UsuarioResponse.of(usuario);
    }

    public UsuarioResponse findByUsuarioSenha(String usuarioParam, String senha) throws NotFoundException {
        var usuarioResponse = findByUsuario(usuarioParam);
        var usuario = validarUsuarioSenha(usuarioResponse.getUsuario(), senha);
        return UsuarioResponse.of(usuario);
    }

    public UsuarioResponse update(UsuarioRequest request) throws NotFoundException {
        var usuario = validarUsuarioExistente(request.getId());
        BeanUtils.copyProperties(request, usuario, "id");
        repository.save(usuario);
        return UsuarioResponse.of(usuario);
    }

    public UsuarioResponse delete(UUID id) throws NotFoundException {
        var usuario = validarUsuarioExistente(id);
        repository.delete(usuario);
        return UsuarioResponse.of(usuario);
    }
}
