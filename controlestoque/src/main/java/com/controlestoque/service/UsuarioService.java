package com.controlestoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.controlestoque.Enums.StatusUsuario;
import com.controlestoque.Repository.Usuarios;
import com.controlestoque.model.Usuario;
import com.controlestoque.service.exception.EmailUsuarioJaCadastradoException;
import com.controlestoque.service.exception.SenhaObrigatoriaUsuarioException;


@Service
public class UsuarioService {
	
	@Autowired
	Usuarios usuRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@SuppressWarnings("deprecation")
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if (usuario.isUsuarioNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if (usuario.isUsuarioNovo()|| !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		
		}
		
		else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
			
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if(!usuario.isUsuarioNovo() && usuario.getAtivo()== null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuRepository.save(usuario);
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuRepository);
	}
}