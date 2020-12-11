package br.com.itau.seguros.desafio.api;

import br.com.itau.seguros.desafio.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.blankOrNullString;


class FeatureToggleControllerIntegrationTest extends IntegrationTest {

    @Test
    void registrarFeatureFlag_consegueRegistrarFlag_tipoToggle() {
        String payload = "{\"nome\":\"teste-toggle\",\"tipo\":\"toggle\",\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_consegueRegistrarFlag_tipoValue() {
        String payload = "{\"nome\":\"teste-value\",\"tipo\":\"value\",\"valor\":123.45,\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_sobrescrever_tipoToggle() {
        String payload = "{\"nome\":\"teste-sobrescrever-toggle\",\"tipo\":\"value\",\"valor\":123.45,\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        payload = "{\"nome\":\"teste-sobrescrever-toggle\",\"tipo\":\"toggle\",\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_sobrescrever_tipoValue() {
        String payload = "{\"nome\":\"teste-sobrescrever-value\",\"tipo\":\"toggle\",\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        payload = "{\"nome\":\"teste-sobrescrever-value\",\"tipo\":\"value\",\"valor\":123.45,\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_erroValidacao_tipoToggle() {
        // Payload SEM o campo "ligado"
        String payload = "{\"nome\":\"teste-validacao-toggle\",\"tipo\":\"toggle\"}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_erroValidacao_tipoValue_campoLigado() {
        // Payload SEM o campo "ligado"
        String payload = "{\"nome\":\"teste-validacao-toggle\",\"tipo\":\"value\",\"valor\":123.45}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_erroValidacao_tipoValue_campoValor() {
        // Payload SEM o campo "valor"
        String payload = "{\"nome\":\"teste-validacao-toggle\",\"tipo\":\"value\",\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body(blankOrNullString());
    }

    @Test
    void registrarFeatureFlag_erroValidacao_tipoValue_campoValorInvalido() {
        // Payload SEM o campo "valor"
        String payload = "{\"nome\":\"teste-validacao-toggle\",\"tipo\":\"value\",\"valor\":\"invalido\"," +
            "\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body(blankOrNullString());
    }

    @Test
    void verificarFeatureFlag_tipoToggle_ativo() {
        String payload = "{\"nome\":\"teste-toggle-ativo\",\"tipo\":\"toggle\",\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        given()
            .contentType(JSON)
            .get("/toggle/teste-toggle-ativo")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());
    }

    @Test
    void verificarFeatureFlag_tipoToggle_inativo() {
        String payload = "{\"nome\":\"teste-toggle-inativo\",\"tipo\":\"toggle\",\"ligado\":false}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        given()
            .contentType(JSON)
            .get("/toggle/teste-toggle-inativo")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body(blankOrNullString());
    }

    @Test
    void verificarFeatureFlag_tipoValue_ativo() {
        String payload = "{\"nome\":\"teste-value-ativo\",\"tipo\":\"value\",\"valor\":123.45,\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        given()
            .contentType(JSON)
            .get("/toggle/teste-value-ativo?valor=1000")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());
    }

    @Test
    void verificarFeatureFlag_tipoValue_inativo_desligado() {
        String payload = "{\"nome\":\"teste-value-inativo-desligado\",\"tipo\":\"value\",\"valor\":123.45," +
            "\"ligado\":false}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        given()
            .contentType(JSON)
            .get("/toggle/teste-value-inativo-desligado?valor=1000")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body(blankOrNullString());
    }

    @Test
    void verificarFeatureFlag_tipoValue_inativo_valor() {
        String payload = "{\"nome\":\"teste-value-inativo-valor\",\"tipo\":\"value\",\"valor\":123.45," +
            "\"ligado\":true}";

        given()
            .contentType(JSON)
            .body(payload)
            .post("/toggle")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(blankOrNullString());

        given()
            .contentType(JSON)
            .get("/toggle/teste-value-inativo-valor?valor=1")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body(blankOrNullString());
    }

    @Test
    void verificarFeatureFlag_toggleInexistente() {
        given()
            .contentType(JSON)
            .get("/toggle/toggle-inexistente")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body(blankOrNullString());
    }

}
