package com.algaworks.coelhofood.domain.exception;

//não precisa declarar o response Http, pois, ele foi declarado na extends
public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradoException(Long cidadeId){
		this(String.format("Cidade de código d% não pode ser removida, pois, está em uso", cidadeId));
	}
}
