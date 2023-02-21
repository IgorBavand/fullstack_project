package com.caixa.caixa.modulos.pedido.dto;

import java.util.List;
import java.util.UUID;

import com.caixa.caixa.modulos.pedido.model.Pedido;
import com.caixa.caixa.modulos.produto.model.Produto;
import com.caixa.caixa.modulos.usuario.model.Usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponse {

    private UUID id;
    // private List<Produto> produtos;
    private Usuario usuario;
    private Double valorTotal;
    private Double preco;

    public static PedidoResponse of(Pedido pedido) {
        return PedidoResponse.builder()
                // .produtos(pedido.getProdutos())
                .usuario(pedido.getUsuario())
                .valorTotal(pedido.getValorTotal())
                .preco(pedido.getPreco())
                .build();
    }

}
