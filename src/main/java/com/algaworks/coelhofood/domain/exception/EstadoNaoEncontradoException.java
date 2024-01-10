package com.algaworks.coelhofood.domain.exception;

//não precisa declarar o response Http, pois, ele foi declarado na extends
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long estadoId){
		this(String.format("Não existe um cadastro para o Estado com código %d", estadoId));
	}
}
