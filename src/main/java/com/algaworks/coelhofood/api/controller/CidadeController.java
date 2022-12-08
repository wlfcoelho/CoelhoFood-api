package com.algaworks.coelhofood.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.algaworks.coelhofood.api.exceptionhandler.Problema;
import com.algaworks.coelhofood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.coelhofood.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.coelhofood.domain.model.Cidade;
import com.algaworks.coelhofood.domain.repository.CidadeRepository;
import com.algaworks.coelhofood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "v1/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping(value = "/listar")
    public List<Cidade> lista() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {

        return cadastroCidade.bucarOuFalhar(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cadastroCidade.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                            @RequestBody Cidade cidade) {

        Cidade cidadeAtual = cadastroCidade.bucarOuFalhar(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try {
            return cadastroCidade.salvar(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);

    }
}