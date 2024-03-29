package com.controlestoque.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.controlestoque.model.GrupoUser;
import com.controlestoque.model.Usuario;

public class UsuarioSistema implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSistema(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		List<GrupoUser> grupoUser = this.usuario.getGrupoUser();
		grupoUser.forEach(p -> authorities.add(new SimpleGrantedAuthority("ROLE_" + p.getNome())));

		return authorities;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getNome() {
		return usuario.getApelido();
	}

}
