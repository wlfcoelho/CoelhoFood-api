package com.algaworks.coelhofood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/v1/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cosinhaRepository;
	
	@GetMapping("/listar")
	public List<Cozinha> lista(){
		return cosinhaRepository.listar();
		
	}
}
