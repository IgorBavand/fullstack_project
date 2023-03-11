package com.caixa.caixa.modulos.pedido.repository;

import com.caixa.caixa.modulos.pedido.model.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, UUID>, CrudRepository<Pedido, UUID> {
}