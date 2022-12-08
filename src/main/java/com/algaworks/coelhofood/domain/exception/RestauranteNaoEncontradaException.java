package com.algaworks.coelhofood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //reason = "Entidade não encontrada")
public class RestauranteNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradaException (Long restauranteId){
		this(String.format("Não existe um cadastro do restaurante com código %d", restauranteId));
	}
}
