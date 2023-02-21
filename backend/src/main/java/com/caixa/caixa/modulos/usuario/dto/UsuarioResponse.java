package com.caixa.caixa.modulos.usuario.dto;

import com.caixa.caixa.modulos.pedido.model.Pedido;
import com.caixa.caixa.modulos.usuario.model.Endereco;
import com.caixa.caixa.modulos.usuario.model.Usuario;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UsuarioResponse {

    private UUID id;
    private String usuario;
    private String senha;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;
    private Pedido pedido;

    public static UsuarioResponse of(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .usuario(usuario.getUsuario())
                .senha(usuario.getSenha())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .endereco(usuario.getEndereco())
                .pedido(usuario.getPedido())
                .build();
    }

}
