package com.algaworks.coelhofood.domain.model;

import com.algaworks.coelhofood.core.validation.Groups;
import com.algaworks.coelhofood.core.validation.Multiplo;
import com.algaworks.coelhofood.core.validation.ValorZeroIncluiDescricao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome",
descricaoObrigatoria = "Frete Grátis")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@NotNull
	//@NotEmpty
	@NotBlank
    @Column(nullable = false)
    private String nome;

	//neste caso o primeiro limita o do zero pra frente, o segundo númeroso positivos ou zero
    //@DecimalMin("0")
    @NotNull
    @PositiveOrZero
    //@TaxaFrete - anotação criada para exemplificar as possibilidades
    @Multiplo(numero = 5)
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne//(fetch = FetchType.LAZY)//ele só faz o select no banco quando é necessário
    //manyToOne é EAGER-lerdo ManyTomany é LAZY-rápido
    //@JsonIgnoreProperties("hibernateLazyInitializer") neste caso aqui, daria erro sem o ignore e sem está
    //anotação, então nós colocamos a anotação q ignora o que está dentro de cozinha
    //@JsonIgnore
    //ele cria uma coluna no sql - BD - , nullable = false indica que este campo não pode ser null
	@JoinColumn(name = "cozinha_id", nullable = false)
	@NotNull
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	//essa anotação valida os dados que estão dentro de cozinha
	@Valid
	private Cozinha cozinha;

    @Embedded
    private Endereço endereço;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
