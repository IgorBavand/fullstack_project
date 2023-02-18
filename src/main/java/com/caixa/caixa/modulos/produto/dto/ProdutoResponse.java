package com.caixa.caixa.modulos.produto.dto;

import com.caixa.caixa.modulos.produto.model.Produto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProdutoResponse {

    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;
    private String imagem;


    public static ProdutoResponse of(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .descricao(produto.getDescricao())
                .estoque(produto.getEstoque())
                .imagem(produto.getImagem())
                .build();
    }

}
