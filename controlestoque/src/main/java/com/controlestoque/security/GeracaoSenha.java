package com.controlestoque.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeracaoSenha {
	
	
	public static void main(String[] args) {
		BCryptPasswordEncoder endecor = new BCryptPasswordEncoder();
		System.out.println(endecor.encode("admin"));
		
	}

}
