
package com.algaworks.coelhofood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.coelhofood.CoelhofoodApiApplication;
import com.algaworks.coelhofood.domain.model.Estado;
import com.algaworks.coelhofood.domain.repository.EstadoRepository;

public class ConsultaEstadoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new SpringApplicationBuilder(CoelhofoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		List <Estado> estados = estadoRepository.listar();
		
		for (Estado estado : estados ) {
			System.out.println(estado.getNome());
		}
	}

}
