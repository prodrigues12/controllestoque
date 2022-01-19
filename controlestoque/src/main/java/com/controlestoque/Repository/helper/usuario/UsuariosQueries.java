package com.controlestoque.Repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controlestoque.Repository.filter.UsuarioFilter;
import com.controlestoque.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);

	public List<String> permissoes(Usuario usuario);

	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);

	public Usuario buscarComGrupos(Long codigo);

}
