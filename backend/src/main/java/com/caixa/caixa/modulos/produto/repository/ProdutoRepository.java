package com.caixa.caixa.modulos.produto.repository;

import com.caixa.caixa.modulos.produto.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, UUID>, CrudRepository<Produto, UUID> {
}