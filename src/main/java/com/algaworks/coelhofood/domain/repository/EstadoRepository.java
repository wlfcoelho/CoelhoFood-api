package com.algaworks.coelhofood.domain.repository;

import java.util.List;

import com.algaworks.coelhofood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Long id);
	
}
