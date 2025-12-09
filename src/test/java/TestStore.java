import io.restassured.path.json.JsonPath;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStore {
    static String ct = "application/json";
    static String uriStore = "https://petstore.swagger.io/v2/store";
    static String pathJson = "src/test/resources/json/store.json";
    String orderId;

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @BeforeAll
    public static void globalSetup() throws IOException {
        RestAssured.filters(new AllureRestAssured());
        String json = lerArquivoJson(pathJson);
        // orderId será lido em setup para cada teste
    }

    @BeforeEach
    public void setup() throws IOException {
        String json = lerArquivoJson(pathJson);
        orderId = String.valueOf(JsonPath.from(json).getInt("id"));
    }

    @Test
    @Order(1)
    @DisplayName("Deve fazer um pedido")
    public void testPostOrder() throws IOException {
        String jsonBody = lerArquivoJson(pathJson);
        String jsonEsperado = lerArquivoJson(pathJson);
        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriStore + "/order")
        .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonPathEsperado.getInt("id")))
                .body("petId", equalTo(jsonPathEsperado.getInt("petId")))
                .body("quantity", equalTo(jsonPathEsperado.getInt("quantity")))
                .body("status", equalTo(jsonPathEsperado.getString("status")))
                .body("complete", equalTo(jsonPathEsperado.getBoolean("complete")));
    }

    @Test
    @Order(2)
    @DisplayName("Deve buscar um pedido")
    public void testGetOrder() throws IOException {
        String jsonEsperado = lerArquivoJson(pathJson);
        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);

        given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriStore + "/order/" + orderId)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonPathEsperado.getInt("id")))
                .body("petId", equalTo(jsonPathEsperado.getInt("petId")));
    }
    @Test
    @Order(3)
    @DisplayName("Deve buscar status dos Pet")
    public void testGetStatus()  {
        given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriStore + "/inventory")
                .then()
                .log().all()
                .statusCode(200)
                .body("",instanceOf(Map.class));
    }
    @Test
    @Order(4)
    @DisplayName("Deve deletar um pedido")
    public void testDeleteOrder() throws IOException {
        given()
                .contentType(ct)
                .log().all()
                .when()
                .delete(uriStore + "/order/" + orderId)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo(orderId))
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"));

    }



}
