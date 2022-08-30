package com.algaworks.coelhofood.domain.exception;

public class EntidadeEmUsoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}
