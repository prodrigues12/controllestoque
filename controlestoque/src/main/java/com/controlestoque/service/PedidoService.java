package com.controlestoque.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.controlestoque.Repository.FuncionarioRepository;

public class PedidoService {
	
	@Autowired
	FuncionarioRepository funRepository;

//	##Pegar data e hora no atual
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public Long tenhoCadastro(Long id) {
		 Long usuario = funRepository.tenhoCadastro(id);
		return usuario;
	}
}
