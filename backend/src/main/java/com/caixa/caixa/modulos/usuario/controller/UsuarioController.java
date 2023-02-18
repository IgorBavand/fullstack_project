package com.caixa.caixa.modulos.usuario.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.caixa.modulos.usuario.dto.UsuarioRequest;
import com.caixa.caixa.modulos.usuario.dto.UsuarioResponse;
import com.caixa.caixa.modulos.usuario.service.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponse> findById(@RequestPart UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestPart UsuarioRequest request) {
        return ResponseEntity.ok().body(service.save(request));
    }
    
}
