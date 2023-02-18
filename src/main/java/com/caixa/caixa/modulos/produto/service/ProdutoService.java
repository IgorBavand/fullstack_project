package com.caixa.caixa.modulos.produto.service;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.comum.exception.NotFoundException;
import com.caixa.caixa.modulos.comum.utils.SalvarImagemUtils;
import com.caixa.caixa.modulos.produto.dto.ProdutoRequest;
import com.caixa.caixa.modulos.produto.dto.ProdutoResponse;
import com.caixa.caixa.modulos.produto.model.Produto;
import com.caixa.caixa.modulos.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.caixa.caixa.modulos.comum.utils.RadomStingUtils.generateRandomString;

@Service
@Slf4j
public class ProdutoService {

    private static final NotFoundException ERR_PRODUTO_NOT_FOUND = new NotFoundException("Produto não encontrado.");

    @Value("${imagem.disco.raiz}")
    private String raiz;

    @Value("${imagem.disco.diretorio-fotos}")
    private String diretorioFotos;

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private SalvarImagemUtils salvarImagemUtils;

    public Page<ProdutoResponse> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(ProdutoResponse::of);
    }

    @Transactional
    public ProdutoResponse save(ProdutoRequest request, MultipartFile imagem) {

        if (imagem != null) {
            String[] urlImagem = (raiz + "/" + diretorioFotos + "/" + imagem.getOriginalFilename()).split(".");
            String extensao = urlImagem[1];

            log.info("salvando produto");
            salvarImagemUtils.salvarFoto(imagem);
        }

        System.out.println(generateRandomString());

        var produto = repository.save(Produto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .estoque(request.getEstoque())
                .build());

        return ProdutoResponse.of(produto);
    }

    public ProdutoResponse delete(UUID id) throws ChangeSetPersister.NotFoundException {
        var produto = validarProdutoExistente(id);
        repository.delete(produto);
        return ProdutoResponse.of(produto);
    }

    public ProdutoResponse update(ProdutoRequest request, UUID id) throws ChangeSetPersister.NotFoundException {
        var produto = validarProdutoExistente(id);

        BeanUtils.copyProperties(request, produto, "id");

        repository.save(produto);
        return ProdutoResponse.of(produto);
    }

    private Produto validarProdutoExistente(UUID id) throws ChangeSetPersister.NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> ERR_PRODUTO_NOT_FOUND);
    }

}
