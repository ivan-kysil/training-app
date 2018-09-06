package ikysil.training.ws.api.v1;

import ikysil.training.config.IntegrationTestConfig;
import ikysil.training.dao.OrderDao;
import ikysil.training.ws.api.v1.dto.OrderDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest(
        classes = IntegrationTestConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OrdersResourceTest {

    @Autowired
    OrderDao dao;

    public static final String BASE_URL = "/ws/api/v1/orders/";

    @LocalServerPort
    private int port;

    private OrderDto order1 = new OrderDto("ikl11444", "twenty one", "none", "3129", "firstuser@gmail.com");
    private OrderDto order2 = new OrderDto("qwe32577", "twenty two", "none", "4024", "seconduser@gmail.com");
    private OrderDto newOrder = new OrderDto("qwe32577","thirty three", "noneone", "2440", "newuser@gmail.com");

    @Before
    public void setUp() {
        RestAssured.basePath = BASE_URL;
        RestAssured.port = port;
        dao.saveOrder(order1);
        dao.saveOrder(order2);
    }

    @Test
    public void savingOKTest() {
        given().contentType(ContentType.JSON)
                .body(order1, ObjectMapperType.JACKSON_2)
                .when()
                .post()
                .then()
                .statusCode(200);
        given().contentType(ContentType.JSON)
                .body(order2, ObjectMapperType.JACKSON_2)
                .when()
                .post()
                .then()
                .statusCode(200);
        assertThat(dao.findAll()).containsAll(Arrays.asList(order1, order2));
    }

    @Test
    public void saveOrderTest(){
        OrderDto tempOrder = new OrderDto("666","thirty six", "nothing", "2660", "newuser66@gmail.com");
        assertThat(dao.findAll()).hasSize(2);
        given().contentType(ContentType.JSON)
                .body(tempOrder, ObjectMapperType.JACKSON_2)
                .post();
        assertThat(dao.findAll()).hasSize(3);
        assertThat(dao.findOrder(tempOrder.getId())).isNotNull();
    }

    @Test
    public void getByIdTest() {
        given().contentType(ContentType.JSON)
                .when()
                .get(order2.getId())
                .then()
                .body("userId", equalTo(order2.getUserId()))
                .body("deliveryInfo", equalTo(order2.getDeliveryInfo()))
                .statusCode(200);
        given().contentType(ContentType.JSON)
                .when()
                .get(order1.getId())
                .then()
                .body("userId", equalTo(order1.getUserId()))
                .body("deliveryInfo", equalTo(order1.getDeliveryInfo()))
                .statusCode(200);
    }


    @Test
    public void getOrderValidationTest() {
        testValidationError("GET", "12345", 400);
        testValidationError("GET", "qwe", 400);
        testValidationError("GET", "abcd123456", 400);
        testValidationError("GET", "as1234", 400);
        testValidationError("GET", "agt1234", 400);
    }

    @Test
    public void putOrderValidationTest() {
        testValidationError("PUT", "12345", 400);
        testValidationError("PUT", "qwe", 400);
        testValidationError("PUT", "abcd123456", 400);
        testValidationError("PUT", "as1234", 400);
        testValidationError("PUT", "agt1234", 400);
    }

    @Test
    public void deleteOrderValidationTest() {
        testValidationError("DELETE", "12345", 400);
        testValidationError("DELETE", "qwe", 400);
        testValidationError("DELETE", "abcd123456", 400);
        testValidationError("DELETE", "as1234", 400);
        testValidationError("DELETE", "agt1234", 400);
    }

    //Method for reducing the code, I think this is a more flexible solution
    private void testValidationError(String method, String orderId, int statusCode){
        switch (method.toUpperCase()){
            case "PUT":
                given().contentType(ContentType.JSON)
                    .when()
                    .put(orderId)
                    .then()
                    .body("message", equalTo("Validation error. Invalid order id format. Expected format is 'ABC12345'"))
                    .statusCode(statusCode);
                break;
            case "GET":
                given().contentType(ContentType.JSON)
                        .when()
                        .get(orderId)
                        .then()
                        .body("message", equalTo("Validation error. Invalid order id format. Expected format is 'ABC12345'"))
                        .statusCode(statusCode);
                break;
            case "DELETE":
                given().contentType(ContentType.JSON)
                        .when()
                        .delete(orderId)
                        .then()
                        .body("message", equalTo("Validation error. Invalid order id format. Expected format is 'ABC12345'"))
                        .statusCode(statusCode);
                break;

        }
    }



    @Test
    public void getAllTest() {
        given().contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body(containsString("1144"))
                .body(containsString("3257"));
    }

    @Test
    public void updateOrderTest() {
        given().contentType(ContentType.JSON)
                .body(newOrder, ObjectMapperType.JACKSON_2)
                .when()
                .put(order2.getId())
                .then()
                .body("userId", equalTo("2440"))
                .body("deliveryInfo", equalTo(null))
                .body("orderNumber", equalTo("thirty three"))
                .statusCode(200);
        assertThat(dao.findOrder(newOrder.getId()).getUserId()).isEqualTo("2440");
        assertThat(dao.findOrder(newOrder.getId()).getOrderNumber()).isEqualTo("thirty three");
    }

    @Test
    public void deleteOrderTest() {
        given().contentType(ContentType.JSON)
                .when()
                .delete(order1.getId())
                .then()
                .statusCode(200);
        given().contentType(ContentType.JSON)
                .when()
                .delete(order2.getId())
                .then()
                .statusCode(200);
        assertThat(dao.findAll()).doesNotContainAnyElementsOf(Arrays.asList(order1, order2));
    }

    @After
    public void removeAll(){
        dao.deleteAll();
    }
}
