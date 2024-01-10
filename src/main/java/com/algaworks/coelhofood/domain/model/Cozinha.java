package com.algaworks.coelhofood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.coelhofood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = Groups.CozinhaId.class)
	private Long id;

	@NotBlank
	@Column(name = "nome", nullable = false)
	private String nome;	
	
	@JsonIgnore //para ignorar a lista de restaurante, pois, com ela aqui seria um loop ficaria restaurante puxando cozinha sempre
	@OneToMany(mappedBy = "cozinha") //mapeamento de cozinha que está detro da classe restaurante
	private List<Restaurante> restaurantes = new ArrayList<>();
	
}
