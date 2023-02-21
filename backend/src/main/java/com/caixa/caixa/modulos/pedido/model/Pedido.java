package com.caixa.caixa.modulos.pedido.model;

import com.caixa.caixa.modulos.pedido.dto.PedidoRequest;
import com.caixa.caixa.modulos.produto.model.Produto;
import com.caixa.caixa.modulos.usuario.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // @OneToMany
    // private List<Produto> produtos;

    @OneToOne
    private Usuario usuario;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "preco")
    private Double preco;

    public static Pedido of(PedidoRequest request) {
        return Pedido.builder()
                // .produtos(request.getProdutos())
                .usuario(request.getUsuario())
                .valorTotal(request.getValorTotal())
                .preco(request.getPreco())
                .build();
    }

}
