package com.algaworks.coelhofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Cidade;
import com.algaworks.coelhofood.domain.model.Estado;
import com.algaworks.coelhofood.domain.repository.CidadeRepository;
import com.algaworks.coelhofood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	//TODO TESTAR MÉTODOS TANTO CIDADE QUANTO ESTADO
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) throws EntidadeNaoEncontradaException {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);
		
		if (estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro para o estado com o código %d", estadoId));
		}
		cidade.setEstado(estado);
		
		return cidadeRepository.salvar(cidade);
	}
	
	public void excluir (Long cidadeId) throws EntidadeEmUsoException, EntidadeNaoEncontradaException{
		try {
			
			cidadeRepository.remover(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException(
					String.format("Não existe um cadastro de cozinha em c[odigo %d", cidadeId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Cidade de código d% não pode ser removida, pois, está em uso", cidadeId));
		}
	}
}
