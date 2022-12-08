package com.algaworks.coelhofood.domain.exception;

public class RestauranteNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradaException (Long restauranteId){
		this(String.format("Não existe um cadastro do restaurante com código %d", restauranteId));
	}
}
