package com.caixa.caixa.modulos.usuario.repository;

import com.caixa.caixa.modulos.produto.model.Produto;
import com.caixa.caixa.modulos.usuario.model.Usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, UUID>, CrudRepository<Usuario, UUID> {
}
