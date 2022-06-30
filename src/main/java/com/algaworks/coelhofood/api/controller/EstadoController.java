package com.algaworks.coelhofood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.coelhofood.domain.model.Estado;
import com.algaworks.coelhofood.domain.repository.EstadoRepository;

@RequestMapping
@RestController("/v1/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping("/listar")
	public List<Estado> lista(){
		return estadoRepository.listar();
	}
}
