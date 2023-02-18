package com.caixa.caixa.modulos.produto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoRequest {

    private String nome;
    private Double preco;
    private String descricao;
    private Integer estoque;

}
