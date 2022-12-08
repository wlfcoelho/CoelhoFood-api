package com.algaworks.coelhofood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //reason = "Entidade não encontrada")
public class CozinhaNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CozinhaNaoEncontradaException(Long cozinhaId){
		this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
	}
}
