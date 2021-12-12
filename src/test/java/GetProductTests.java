import org.junit.jupiter.api.Test;

import java.util.Properties;

import static io.restassured.RestAssured.when;

public class GetProductTests {
    static Properties properties = new Properties();

    @Test
    void getProductPositiveTest() {
        when()
                .get("http://80.78.248.82:8189/market/api/v1/products/9999")
                .prettyPeek()
                .then()
                .statusCode(404);
    }
}