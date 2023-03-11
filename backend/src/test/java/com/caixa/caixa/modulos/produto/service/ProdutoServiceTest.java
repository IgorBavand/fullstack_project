package com.caixa.caixa.modulos.produto.service;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.produto.model.Produto;
import com.caixa.caixa.modulos.produto.repository.ProdutoRepository;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    public void findAll_deveRetornarUmaPage_quandoSolicitado() {

        var pageRequest = new PageRequest();
        pageRequest.setSize(10);
        pageRequest.setPage(0);

        when(produtoRepository.findAll(eq(pageRequest)))
                .thenReturn(umaPageProdutos(umaListaProdutos()));

        var produtoPage = service.findAll(pageRequest);

        assertEquals(produtoPage.getTotalPages(), 1);
        assertEquals(produtoPage.getTotalElements(), 2);

        assertThat(produtoPage.getContent())
                .hasSize(2)
                .extracting("id", "nome", "imagem", "descricao", "estoque", "preco")
                .containsExactly(
                        tuple(UUID.fromString("4f234df7-efec-4ff5-a87f-0279553d1d61"), "bicicleta", "bike.jpg", "bicicleta azul", 1500, 25.00),
                        tuple(UUID.fromString("61a9816b-09e9-4eb7-81c1-2b6a7900dab5"), "celular", "celular.png", "iphone 12 pro", 2500, 7000.00)
                );

        verify(produtoRepository, times(1))
                .findAll(eq(pageRequest));
    }


    public static Produto umProduto() {
        return Produto.builder()
                .nome("bicicleta")
                .imagem("imagem.jpg")
                .descricao("bicicleta vermelha")
                .estoque(1500)
                .preco(150.00)
                .build();
    }

    public static Produto umProdutoGenerico(UUID uuid, String nome, String img,
                                            String descricao, Integer estoque,
                                            Double preco) {
        return Produto.builder()
                .id(uuid)
                .nome(nome)
                .imagem(img)
                .descricao(descricao)
                .estoque(estoque)
                .preco(preco)
                .build();
    }

    public static List<Produto> umaListaProdutos() {
        return List.of(
                umProdutoGenerico(UUID.fromString("4f234df7-efec-4ff5-a87f-0279553d1d61"),
                        "bicicleta", "bike.jpg", "bicicleta azul", 1500, 25.00),
                umProdutoGenerico(UUID.fromString("61a9816b-09e9-4eb7-81c1-2b6a7900dab5"),
                        "celular", "celular.png", "iphone 12 pro", 2500, 7000.00)
        );
    }

    public Page<Produto> umaPageProdutos(List<Produto> produtos) {
        return new PageImpl<>(
                produtos,
                new PageRequest(),
                produtos.size()
        );
    }


}
