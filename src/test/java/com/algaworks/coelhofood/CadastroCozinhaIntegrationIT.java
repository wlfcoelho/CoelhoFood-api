package com.algaworks.coelhofood;

import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.repository.CozinhaRepository;
import com.algaworks.coelhofood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

//TODO: olhar a classe teste - se possível criar os testes unitários deste api
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIntegrationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @BeforeEach
    public void setup(){
        //mostra o log do que foi passado na requisição e o response
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "v1/cozinhas/listar";

        databaseCleaner.clearTables();
        prepararDados();
    }


    @Test
    public void deveConter2Cozinhas_QuandoConsultarCozinhas(){

                given()
                    .accept(ContentType.JSON)
                .when()
                        .get()
                .then()
                        .body("", hasSize(2));
    }

    @Test
    public void testRetornarStatus201_QuandoCadastroCozinha(){
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
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    private void prepararDados(){
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaRepository.save(cozinha2);
    }
}
