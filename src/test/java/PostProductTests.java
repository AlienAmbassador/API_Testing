import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostProductTests {
    Product product;
    Integer id;


    @Test
    void postProductPositiveTest() {
        product = Product.builder()
                .price(null)
                .title("Banana")
                .categoryTitle("Food")
                .id(null)
                .build();
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(401)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

    @Test
    void postProductWithNullPriseTest() {
        product = Product.builder()
                .price(null)
                .title("Banana")
                .categoryTitle("Food")
                .id(null)
                .build();
        id = given()
                .body(product.toString())
                .header("content-type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(401)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

    @Test
    void postFreeProductTest() {
        product = Product.builder()
                .price(0)
                .title("Banana")
                .categoryTitle("Food")
                .id(null)
                .build();
        id = given()
                .body(product.toString())
                .header("content-type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(201)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

    @Test
    void postProductWithNegativePriceTest() {
        product = Product.builder()
                .price(-100)
                .title("Banana")
                .categoryTitle("Food")
                .id(null)
                .build();
        id = given()
                .body(product.toString())
                .header("content-type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(401)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

    @Test
    void postProductTitleWithIntTest() {
        product = Product.builder()
                .price(100)
                .title("B1n1n1")
                .categoryTitle("Food")
                .id(null)
                .build();
        id = given()
                .body(product.toString())
                .header("content-type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(401)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

    @Test
    void postProductTitleUPPERCaseTest() {
        product = Product.builder()
                .price(100)
                .title("BANANA  ")
                .categoryTitle("Food")
                .id(null)
                .build();
        id = given()
                .body(product.toString())
                .header("content-type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(201)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

    @AfterEach
    void tearDown() {
        when()
                .delete("http://80.78.248.82:8189/market/api/v1/products/{id}", id)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}