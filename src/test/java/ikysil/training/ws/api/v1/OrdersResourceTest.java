package ikysil.training.ws.api.v1;

import ikysil.training.config.IntegrationTestConfig;
import ikysil.training.ws.api.v1.dto.OrderDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.hamcrest.core.IsEqual.equalTo;


@SpringBootTest(
        classes = IntegrationTestConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OrdersResourceTest {

    public static final String BASE_URL = "/ws/api/v1/orders/";

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.basePath = BASE_URL;
        RestAssured.port = port;
    }

    @Test
    // TODO rename test
    public void test() {
        OrderDto request = new OrderDto();

        given().contentType(ContentType.JSON)
                .body(request, ObjectMapperType.JACKSON_2)
                .when()
                .post()
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
    }
}
