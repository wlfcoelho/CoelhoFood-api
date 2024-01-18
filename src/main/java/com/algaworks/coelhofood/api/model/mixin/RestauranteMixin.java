package com.algaworks.coelhofood.api.model.mixin;

import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.model.Endereço;
import com.algaworks.coelhofood.domain.model.FormaPagamento;
import com.algaworks.coelhofood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereço endereço;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
}
