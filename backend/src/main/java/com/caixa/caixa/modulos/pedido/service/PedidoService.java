package com.caixa.caixa.modulos.pedido.service;

import com.caixa.caixa.config.requisicoes.PageRequest;
import com.caixa.caixa.modulos.comum.exception.NotFoundException;
import com.caixa.caixa.modulos.pedido.dto.PedidoRequest;
import com.caixa.caixa.modulos.pedido.dto.PedidoResponse;
import com.caixa.caixa.modulos.pedido.model.Pedido;
import com.caixa.caixa.modulos.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
// @Slf4j
public class PedidoService {

    private static final NotFoundException ERR_PEDIDO_NOT_FOUND = new NotFoundException("Pedido não encontrado.");

    @Autowired
    private PedidoRepository repository;

    private Pedido validarPedidoExistente(UUID id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> ERR_PEDIDO_NOT_FOUND);
    }

    public Page<PedidoResponse> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(PedidoResponse::of);
    }

    public PedidoResponse findById(UUID id) throws NotFoundException {
        var pedido = validarPedidoExistente(id);
        return PedidoResponse.of(pedido);
    }

    @Transactional
    public PedidoResponse save(PedidoRequest request) throws NotFoundException {
        var pedido = repository.save(Pedido.builder()
                .usuario(request.getUsuario())
                .produtos(request.getProdutos())
                .valorTotal(request.getValorTotal())
                .preco(request.getPreco())
                .build());

        return PedidoResponse.of(pedido);
    }

    public PedidoResponse update(PedidoRequest request) throws NotFoundException {
        var pedido = validarPedidoExistente(request.getId());
        BeanUtils.copyProperties(request, pedido, "id");
        repository.save(pedido);
        return PedidoResponse.of(pedido);
    }

}
