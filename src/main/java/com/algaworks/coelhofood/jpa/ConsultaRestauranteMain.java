package com.algaworks.coelhofood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.coelhofood.CoelhofoodApiApplication;
import com.algaworks.coelhofood.domain.model.Restaurante;
import com.algaworks.coelhofood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(CoelhofoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = restauranteRepository.listar();
		
		for (Restaurante restaurante : restaurantes) {
			System.out.printf("%s - %f - %s\n", restaurante.getNome(), restaurante.getTaxaFrete()
					, restaurante.getCozinha().getNome());
		}

	}

}
