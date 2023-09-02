package com.algaworks.coelhofood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.coelhofood.api.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull (groups = Groups.EstadoId.class)
	private Long id;

	@NotBlank
	@Column(name = "nome", nullable = false)
	private String nome;
}
