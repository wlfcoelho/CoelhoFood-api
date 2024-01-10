package com.algaworks.coelhofood;


import com.algaworks.coelhofood.domain.model.Cozinha;
import com.algaworks.coelhofood.domain.model.Restaurante;
import com.algaworks.coelhofood.domain.repository.CozinhaRepository;
import com.algaworks.coelhofood.domain.repository.RestauranteRepository;
import com.algaworks.coelhofood.resource.ResourceUtils;
import com.algaworks.coelhofood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteIntegrationIT {

    //fazer testes de consulta e cadastro de restaurante
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Restaurante restauranteAlemao;

    private Restaurante restauranteTailandes;

    private Cozinha cozinhaAmericana;

    private Cozinha cozinhaJapones;

    private int quantidadeDeRestaurantesCadastradas;

    private String jsonRestauranteAlemao;

    private String jsonRestauranteSemCozinha;

    private String jsonRestauranteSemTaxa;

    private String jsonRestauranteComCozinhaInexistente;

    int RESTAURANTE_ID_INEXISTENTE = 110;

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "v1/restaurantes";
        jsonRestauranteAlemao = ResourceUtils.getContentFromResource(
                "/json/correto/restauranteAlemao.json"
        );
        jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource(
                "/json/incorreto/restauranteSemCozinha"
        );
        jsonRestauranteSemTaxa = ResourceUtils.getContentFromResource(
                "/json/incorreto/restauranteSemTaxa"
        );
        jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource(
                "/json/incorreto/restauranteComCozinhaInexistente"
        );
        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveContarAQuantDeRestaurates_QuandoConsultarRestaurantes() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/listar")
                .then()
                .body("", hasSize(quantidadeDeRestaurantesCadastradas));
    }

    @Test
    public void testeRetornarStatus201_QuandoCadastrarCozinha() {
        given()
                .body(jsonRestauranteAlemao)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void testeRetornarStatus400_QuandoCadastrarSemCozinha() {
        given()
                .body(jsonRestauranteSemCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void testeRetornarStatus400_QuandoCadastrarSemTaxa() {
        given()
                .body(jsonRestauranteSemTaxa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testeRetornarStatus400_QuandoCadastrarCozinhaInexistente() {
        given()
                .body(jsonRestauranteComCozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/listar")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorreto_QuandoConsutarRestauranteExistente() {
        given()
                .pathParam("restauranteId", restauranteAlemao.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(restauranteAlemao.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        given()
                .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    //construir teste do consulta e do cadastro de restaurante
    @Test
    public void retornar200QuandoBuscarRestaurante (){

        given()
                .pathParams("restauranteId", restauranteAlemao.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(restauranteAlemao.getNome()));
    }

    private void prepararDados() {
        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        cozinhaJapones = new Cozinha();
        cozinhaJapones.setNome("Japonês");
        cozinhaRepository.save(cozinhaJapones);

        restauranteAlemao = new Restaurante();
        restauranteAlemao.setCozinha(cozinhaAmericana);

        restauranteAlemao.setNome("Alemão");
        restauranteAlemao.setTaxaFrete(BigDecimal.valueOf(14));
        restauranteRepository.save(restauranteAlemao);

        restauranteTailandes = new Restaurante();
        restauranteTailandes.setCozinha(cozinhaJapones);
        restauranteTailandes.setNome("Tailandes");
        restauranteTailandes.setTaxaFrete(BigDecimal.valueOf(14));
        restauranteRepository.save(restauranteTailandes);

        quantidadeDeRestaurantesCadastradas = (int) restauranteRepository.count();
    }
}
