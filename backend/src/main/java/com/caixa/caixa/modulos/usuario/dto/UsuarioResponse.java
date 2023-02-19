package com.caixa.caixa.modulos.usuario.dto;

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

    public static UsuarioResponse of(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .usuario(usuario.getUsuario())
                .senha(usuario.getSenha())
                .nome(usuario.getNome())
                .build();
    }

}
