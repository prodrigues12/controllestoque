package com.controlestoque.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.controlestoque.Repository.Usuarios;
import com.controlestoque.model.Usuario;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Usuarios usuRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuRepository.porEmailEAtivo(email);
		Usuario usuario = usuarioOptional
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return new UsuarioSistema(usuario);
	}

}
