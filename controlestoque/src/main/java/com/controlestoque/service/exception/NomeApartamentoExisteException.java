package com.controlestoque.service.exception;

public class NomeApartamentoExisteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NomeApartamentoExisteException(String message) {
		super(message);
	}

}
