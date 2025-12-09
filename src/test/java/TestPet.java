import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

@ExtendWith({AllureJunit5.class, AllureTestWatcher.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPet {
    static String ct = "application/json";
    static String uriPet = "https://petstore.swagger.io/v2/pet";
    String petId = "231985";

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @BeforeAll
    public static void globalSetup() {
        // registra filtro do Allure para capturar requests/responses
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Order(1)
    @DisplayName("Deve incluir um Pet")
    public void testPostPet() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");
        String jsonEsperado = lerArquivoJson("src/test/resources/json/pet1.json");
        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriPet)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonPathEsperado.getInt("id")))
                .body("category.id", equalTo(jsonPathEsperado.getInt("category.id")))
                .body("category.name", equalTo(jsonPathEsperado.getString("category.name")))
                .body("name", equalTo(jsonPathEsperado.getString("name")))
                .body("photoUrls[0]", equalTo(jsonPathEsperado.getString("photoUrls[0]")))
                .body("tags[0].id", equalTo(jsonPathEsperado.getInt("tags[0].id")))
                .body("tags[0].name", equalTo(jsonPathEsperado.getString("tags[0].name")))
                .body("status", equalTo(jsonPathEsperado.getString("status")));


    }

    @Test
    @Order(2)
    @DisplayName("Deve buscar um Pet")
    public void testGetPet() throws IOException {
        String jsonEsperado = lerArquivoJson("src/test/resources/json/pet1.json");
        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);
        given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriPet + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonPathEsperado.getInt("id")))
                .body("category.id", equalTo(jsonPathEsperado.getInt("category.id")))
                .body("category.name", equalTo(jsonPathEsperado.getString("category.name")))
                .body("name", equalTo(jsonPathEsperado.getString("name")))
                .body("photoUrls[0]", equalTo(jsonPathEsperado.getString("photoUrls[0]")))
                .body("tags[0].id", equalTo(jsonPathEsperado.getInt("tags[0].id")))
                .body("tags[0].name", equalTo(jsonPathEsperado.getString("tags[0].name")))
                .body("status", equalTo(jsonPathEsperado.getString("status")));
    }

    @Test
    @Order(3)
    @DisplayName("Deve atualizar um Pet")
    public void testPutPet() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/pet2.json");
        String jsonEsperado = lerArquivoJson("src/test/resources/json/pet2.json");
        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
                .when()
                .put(uriPet)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonPathEsperado.getInt("id")))
                .body("category.id", equalTo(jsonPathEsperado.getInt("category.id")))
                .body("category.name", equalTo(jsonPathEsperado.getString("category.name")))
                .body("name", equalTo(jsonPathEsperado.getString("name")))
                .body("photoUrls[0]", equalTo(jsonPathEsperado.getString("photoUrls[0]")))
                .body("tags[0].id", equalTo(jsonPathEsperado.getInt("tags[0].id")))
                .body("tags[0].name", equalTo(jsonPathEsperado.getString("tags[0].name")))
                .body("status", equalTo(jsonPathEsperado.getString("status")));

    }

    @Test
    @Order(4)
    @DisplayName("Deve buscar o Pet Atualizado")
    public void testGetPet2() throws IOException {
        String jsonEsperado = lerArquivoJson("src/test/resources/json/pet2.json");
        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);
        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriPet + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonPathEsperado.getInt("id")))
                .body("category.id", equalTo(jsonPathEsperado.getInt("category.id")))
                .body("category.name", equalTo(jsonPathEsperado.getString("category.name")))
                .body("name", equalTo(jsonPathEsperado.getString("name")))
                .body("photoUrls[0]", equalTo(jsonPathEsperado.getString("photoUrls[0]")))
                .body("tags[0].id", equalTo(jsonPathEsperado.getInt("tags[0].id")))
                .body("tags[0].name", equalTo(jsonPathEsperado.getString("tags[0].name")))
                .body("status", equalTo(jsonPathEsperado.getString("status")));
    }

   @Test
    @Order(5)
    @DisplayName("Deve deletar um Pet")
    public void testDeletePet() throws IOException {
        given()
                .contentType(ct)
                .log().all()
                .when()
                .delete(uriPet + "/" + petId)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo(petId))
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"));

    }



}
