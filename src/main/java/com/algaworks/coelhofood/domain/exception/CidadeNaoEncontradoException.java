package com.algaworks.coelhofood.domain.exception;

//não precisa declarar o response Http, pois, ele foi declarado na extends
public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradoException(Long cidadeId){
		this(String.format("Não existe um cadastro para cidade com código %d", cidadeId));
	}
}
