package com.algaworks.coelhofood;

import static org.hamcrest.Matchers.equalTo;

import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.repository.CozinhaRepository;
import com.algaworks.coelhofood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")

//TODO: olhar a classe teste - se possível criar os testes unitários deste api
/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
*/
public class CadastroCozinhaIntegrationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Before
    public void setup() {
        //mostra o log do que foi passado na requisição e o response
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "v1/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();
    }


    @Test
    public void deveConter2Cozinhas_QuandoConsultarCozinhas() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/listar")
                .then()
                .body("", hasSize(2));
    }

    @Test
    public void testRetornarStatus201_QuandoCadastroCozinha() {
        given()
                .body("{ \"nome\": \"Chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.ANY)
                .when()
                .get("/listar")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorreto_QuandConsultaCozinhaExistente() {
                given()
                    .pathParams("cozinhaId", 2)
                    .accept(ContentType.JSON)
                .when()
                    .get("/{cozinhaId}")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("nome", equalTo("Americana"));
    }

    @Test
    public void deveRetornarStatus404_QuandConsultaCozinhaInexistente() {
                given()
                    .pathParams("cozinhaId", 100)
                    .accept(ContentType.JSON)
                .when()
                    .get("/{cozinhaId}")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }


    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaRepository.save(cozinha2);
    }
}
