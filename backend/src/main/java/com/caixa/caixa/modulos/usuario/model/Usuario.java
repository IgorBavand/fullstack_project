package com.caixa.caixa.modulos.usuario.model;

import com.caixa.caixa.modulos.pedido.model.Pedido;
import com.caixa.caixa.modulos.usuario.dto.UsuarioRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    @Column(name = "usuario", unique = true, nullable = false, length = 20)
    private String usuario;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "senha", nullable = false, length = 50)
    private String senha;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pedido pedido;

    public static Usuario of(UsuarioRequest request) {
        return Usuario.builder()
                .usuario(request.getUsuario())
                .senha(request.getSenha())
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .endereco(request.getEndereco())
                .pedido(request.getPedido())
                .build();
    }

}
