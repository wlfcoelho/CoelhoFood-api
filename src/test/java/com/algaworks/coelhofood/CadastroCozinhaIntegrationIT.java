package com.algaworks.coelhofood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

//TODO: olhar a classe teste - se possível criar os testes unitários deste api
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CadastroCozinhaIntegrationIT {

    @LocalServerPort
    private int port;
    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas(){
        //mostra o log do que foi passado na requisição e o response
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

                given()
                    .basePath("v1/cozinhas/listar")
                    .port(port)
                    .accept(ContentType.JSON)
                .when()
                        .get()
                .then()
                        .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter4Cozinhas_QuandoConsultarCozinhas(){
        //mostra o log do que foi passado na requisição e o response
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

                given()
                    .basePath("v1/cozinhas/listar")
                    .port(port)
                    .accept(ContentType.JSON)
                .when()
                        .get()
                .then()
                        .body("", hasSize(4))
                        .body("nome", hasItems("Tailandesa", "Indiana"));
    }
}
