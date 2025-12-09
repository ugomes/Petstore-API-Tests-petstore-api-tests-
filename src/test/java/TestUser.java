import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AllureJunit5.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUser {
    static String ct = "application/json";
    static String uriUser = "https://petstore.swagger.io/v2/user";
    String userName = "teste";
    String userId = "231985";



    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @BeforeAll
    public static void globalSetup() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Order(1)
    @DisplayName("Deve Postar um usuário")
    public void testPostUser() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
        String userId = "231985";

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo(userId))
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"));
              }

    @Test
    @Order(2)
    @DisplayName("Deve buscar um usuário")
    public void testGetUser() throws IOException {



        String jsonEsperado = lerArquivoJson("src/test/resources/json/user1.json");


        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + "/" + userName)
        .then()
                .log().all()
                .statusCode(200)
                // Compara campo por campo
                .body("username", equalTo(jsonPathEsperado.getString("username")))
                .body("firstName", equalTo(jsonPathEsperado.getString("firstName")))
                .body("lastName", equalTo(jsonPathEsperado.getString("lastName")))
                .body("email", equalTo(jsonPathEsperado.getString("email")))
                .body("password", equalTo(jsonPathEsperado.getString("password")))
                .body("phone", equalTo(jsonPathEsperado.getString("phone")))
                .body("userStatus", equalTo(jsonPathEsperado.getInt("userStatus")));
    }
    @Test
    @Order(3)
    @DisplayName("Deve atualizar um usuário")
    public void testPutUser() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
                .when()
                .put(uriUser + "/" + userName)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo(userId))
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"));

    }
    @Test
    @Order(4)
    @DisplayName("Deve buscar um usuário atualizado")
    public void testGetUser2() throws IOException {
        String jsonEsperado = lerArquivoJson("src/test/resources/json/user2.json");

        JsonPath jsonPathEsperado = JsonPath.from(jsonEsperado);
        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + "/" + userName)
                .then()
                .log().all()
                .statusCode(200)

      
                .body("username", equalTo(jsonPathEsperado.getString("username")))
                .body("firstName", equalTo(jsonPathEsperado.getString("firstName")))
                .body("lastName", equalTo(jsonPathEsperado.getString("lastName")))
                .body("email", equalTo(jsonPathEsperado.getString("email")))
                .body("password", equalTo(jsonPathEsperado.getString("password")))
                .body("phone", equalTo(jsonPathEsperado.getString("phone")))
                .body("userStatus", equalTo(jsonPathEsperado.getInt("userStatus")));


    }
    @Test
    @Order(5)
    @DisplayName("Deve deletar um usuário")
    public void testDeleteUser() throws IOException {

        given()
                .contentType(ct)
                .log().all()
        .when()
                .delete(uriUser + "/" + userName)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo(userName))
                .body("code",equalTo(200))
                .body("type",equalTo("unknown"));
    }



}
