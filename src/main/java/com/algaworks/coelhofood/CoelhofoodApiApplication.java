package com.algaworks.coelhofood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaworks.coelhofood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class CoelhofoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoelhofoodApiApplication.class, args);
	}

}
