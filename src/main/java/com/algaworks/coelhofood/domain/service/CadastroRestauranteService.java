package com.algaworks.coelhofood.domain.service;

import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.exception.RestauranteNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.model.Restaurante;
import com.algaworks.coelhofood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarOuFalhar (Long restauranteId){
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradaException(restauranteId));
	}
}