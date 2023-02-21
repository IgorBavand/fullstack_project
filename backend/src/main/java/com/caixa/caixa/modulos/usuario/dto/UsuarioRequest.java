package com.caixa.caixa.modulos.usuario.dto;

import java.util.UUID;

import com.caixa.caixa.modulos.pedido.model.Pedido;
import com.caixa.caixa.modulos.usuario.model.Endereco;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequest {

    private UUID id;
    private String usuario;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private Endereco endereco;
    private Pedido pedido;

}
