package com.algaworks.coelhofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.model.Restaurante;
import com.algaworks.coelhofood.domain.repository.CozinhaRepository;
import com.algaworks.coelhofood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) throws EntidadeNaoEncontradaException {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro para a cozinha com código %d", cozinhaId));
		}

		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
	

	public void excluir(Long restauranteId) throws EntidadeEmUsoException, EntidadeNaoEncontradaException {
		try {
			restauranteRepository.remover(restauranteId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException(
					String.format("Não existe um cadastro de cozinha em código %d", restauranteId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Restaurante de código %d não pode ser removida, pois, está em uso", restauranteId));

		}
	}

}