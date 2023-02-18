package com.caixa.caixa.modulos.produto.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProdutoRequest {

    private String nome;
    private Double preco;
    private String descricao;
    private Integer estoque;

}
