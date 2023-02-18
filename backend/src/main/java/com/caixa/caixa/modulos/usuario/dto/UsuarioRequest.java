package com.caixa.caixa.modulos.usuario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequest {

    private String usuario;
    private String nome;
    private String senha;

}
