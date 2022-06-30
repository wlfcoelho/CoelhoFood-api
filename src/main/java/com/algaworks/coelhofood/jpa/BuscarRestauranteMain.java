package com.algaworks.coelhofood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.coelhofood.CoelhofoodApiApplication;
import com.algaworks.coelhofood.domain.model.Restaurante;
import com.algaworks.coelhofood.domain.repository.RestauranteRepository;

public class BuscarRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(CoelhofoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restauranteRepository.buscar(1L);
		
		System.out.println(restaurante.getNome());
	}
}
