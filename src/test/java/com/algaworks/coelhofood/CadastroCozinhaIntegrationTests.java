package com.algaworks.coelhofood;

import com.algaworks.coelhofood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.coelhofood.domain.exception.EntidadeEmUsoException;
import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.service.CadastroCozinhaService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: olhar a classe teste - se possível criar os testes unitários deste api
@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroCozinhaIntegrationTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    public void testarCadastroCozinhaComSucesso() {
        //cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        //ação
        novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

        //validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalharAoCadastrarCozinhaQuandoSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

    }

    //Junit 4
    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        cadastroCozinhaService.excluir(1L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        cadastroCozinhaService.excluir(11000L);
    }

    //Junit 5
    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUsoJ5() {

        EntidadeEmUsoException erroEsperado =
                Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
                    cadastroCozinhaService.excluir(1L);
                });

        assertThat(erroEsperado).isNotNull();

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistenteJ5() {


        CozinhaNaoEncontradaException erroEsperado =
                Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
                    cadastroCozinhaService.excluir(100L);
                });

        assertThat(erroEsperado).isNotNull();

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUsoError(){
        EntidadeEmUsoException erro =
                Assertions.assertThrows(EntidadeEmUsoException.class, () ->{
                   cadastroCozinhaService.excluir(1L);
                });

        assertThat(erro).isNotNull();

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistenteErro(){
        CozinhaNaoEncontradaException erro =
                Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
                  cadastroCozinhaService.excluir(101L);
        });

        assertThat(erro).isNotNull();


    }
}
