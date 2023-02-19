package com.caixa.caixa.modulos.produto.model;

import com.caixa.caixa.modulos.produto.dto.ProdutoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

    @Column(name = "imagem")
    private String imagem;

    public static Produto of(ProdutoRequest request, String imagem) {
        return Produto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .imagem(imagem)
                .estoque(request.getEstoque())
                .build();
    }

}
