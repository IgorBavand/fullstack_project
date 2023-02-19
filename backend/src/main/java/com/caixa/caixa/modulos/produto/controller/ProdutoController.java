package com.caixa.caixa.modulos.produto.controller;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.comum.exception.NotFoundException;
import com.caixa.caixa.modulos.produto.dto.ProdutoRequest;
import com.caixa.caixa.modulos.produto.dto.ProdutoResponse;
import com.caixa.caixa.modulos.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> getAllProdutos(PageRequest pageRequest) {
        return ResponseEntity.ok().body(service.findAll(pageRequest));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponse> getProduto(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProdutoResponse> save(@RequestPart ProdutoRequest request, MultipartFile imagem) {
        return ResponseEntity.ok().body(service.save(request, imagem));
    }

    @PutMapping
    public ResponseEntity<ProdutoResponse> update(@RequestBody ProdutoRequest request) throws NotFoundException {
        return ResponseEntity.ok().body(service.update(request));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<ProdutoResponse> delete(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok().body(service.delete(id));
    }

}
