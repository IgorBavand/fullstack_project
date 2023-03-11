package com.caixa.caixa.modulos.pedido.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.pedido.dto.PedidoRequest;
import com.caixa.caixa.modulos.pedido.dto.PedidoResponse;
import com.caixa.caixa.modulos.pedido.service.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> getAllProdutos(PageRequest pageRequest) {
        return ResponseEntity.ok().body(service.findAll(pageRequest));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> save(@RequestBody PedidoRequest request) {
        return ResponseEntity.ok().body(service.save(request));
    }

    @PutMapping
    public ResponseEntity<PedidoResponse> update(@RequestBody PedidoRequest request) {
        return ResponseEntity.ok().body(service.update(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
