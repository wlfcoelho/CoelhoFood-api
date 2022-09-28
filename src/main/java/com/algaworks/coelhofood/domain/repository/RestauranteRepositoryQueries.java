package com.algaworks.coelhofood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.coelhofood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	List<Restaurante> findComfreteGratis(String nome);
}