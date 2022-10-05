package com.algaworks.coelhofood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //spring ignora a implementação e assim ele não instância
public interface CustomJpaRepository <T, ID> extends JpaRepository<T, ID> {

	Optional<T> buscarPrimeiro();
	
	
}
