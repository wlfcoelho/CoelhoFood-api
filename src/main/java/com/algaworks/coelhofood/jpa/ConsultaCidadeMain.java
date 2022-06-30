package com.algaworks.coelhofood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.coelhofood.CoelhofoodApiApplication;
import com.algaworks.coelhofood.domain.model.Cidade;
import com.algaworks.coelhofood.domain.repository.CidadeRepository;

public class ConsultaCidadeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new SpringApplicationBuilder(CoelhofoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		
		List <Cidade> cidades = cidadeRepository.listar();
		
		for (Cidade cidade : cidades) {
			System.out.println(cidade.getNome());
		}
	}

}
