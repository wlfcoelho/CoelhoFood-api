package com.algaworks.coelhofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.coelhofood.domain.model.Cidade;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
