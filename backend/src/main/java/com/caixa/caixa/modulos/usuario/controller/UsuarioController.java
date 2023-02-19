package com.caixa.caixa.modulos.usuario.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.usuario.dto.UsuarioRequest;
import com.caixa.caixa.modulos.usuario.dto.UsuarioResponse;
import com.caixa.caixa.modulos.usuario.service.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody UsuarioRequest request) {
        return ResponseEntity.ok().body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> getAllUsuarios(PageRequest pageRequest) {
        return ResponseEntity.ok().body(service.findAll(pageRequest));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<UsuarioResponse> delete(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
    
    @PutMapping
    public ResponseEntity<UsuarioResponse> update(@RequestBody UsuarioRequest request) {
        return ResponseEntity.ok().body(service.update(request));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<UsuarioResponse> findByUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok().body(service.findByUsuario(usuario));
    }

    @GetMapping("{usuario}/{senha}")
    public ResponseEntity<UsuarioResponse> findByUsuarioSenha(@PathVariable String usuario, @PathVariable String senha) {
        return ResponseEntity.ok().body(service.findByUsuarioSenha(usuario, senha));
    }
    
}
