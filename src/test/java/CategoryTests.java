import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoryTests {
    public static final String CATEGORY_ENDPOINT = "categories/{id}";
    static Properties properties = new Properties();

    @BeforeEach
    void setUp() throws IOException {
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        properties.getProperty("baseURL");
        RestAssured.baseURI = properties.getProperty("baseURL");
    }

    @Test
    void getCategoryTest() {
        given()
                .when()
                .get(CATEGORY_ENDPOINT, 1)
                .then()
                .statusCode(200);
    }

    @Test
    void getCategoryWithLogsTest() {
        given()
                .when()
                .log()
                .method()
                .log()
                .uri()
                .log()
                .body()
                .when()
                .get("/categories/1")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getCategoryWithAssertsForResponseTest() {
        Response response = given()
                .when()
                .log()
                .method()
                .log()
                .uri()
                .log()
                .body()
                .when()
                .get("/categories/1")
                .prettyPeek();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.body().jsonPath().get("products[0].categoryTitle"), equalTo("Food"));
    }
}

