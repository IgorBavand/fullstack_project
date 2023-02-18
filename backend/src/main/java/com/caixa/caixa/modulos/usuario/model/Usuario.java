package com.caixa.caixa.modulos.usuario.model;

import com.caixa.caixa.modulos.usuario.dto.UsuarioRequest;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="usuario", nullable=false, length=50)
    private String usuario;

    @Column(name = "nome", nullable=false, length=50)
    private String nome;

    @Column(name = "senha", nullable=false, length=50)
    private String senha;

    public static Usuario of(UsuarioRequest request) {
        return Usuario.builder()
                .usuario(request.getUsuario())
                .senha(request.getSenha())
                .nome(request.getNome())
                .build();
    }

}
