package com.caixa.caixa.modulos.pedido.dto;

import java.util.List;
import java.util.UUID;

import com.caixa.caixa.modulos.produto.model.Produto;
import com.caixa.caixa.modulos.usuario.model.Usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoRequest {

    private UUID id;
    // private List<Produto> produtos;
    private Usuario usuario;
    private Double valorTotal;
    private Double preco;

}
