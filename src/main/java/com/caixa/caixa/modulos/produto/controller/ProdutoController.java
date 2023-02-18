package com.caixa.caixa.modulos.produto.controller;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.produto.dto.ProdutoRequest;
import com.caixa.caixa.modulos.produto.dto.ProdutoResponse;
import com.caixa.caixa.modulos.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProdutoResponse> save(@RequestPart ProdutoRequest request, MultipartFile imagem) {
        return ResponseEntity.ok().body(service.save(request, imagem));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProdutoResponse> delete(@PathVariable UUID id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok().body(service.delete(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponse> update(@RequestBody ProdutoRequest request, @PathVariable UUID id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok().body(service.update(request, id));
    }
}
