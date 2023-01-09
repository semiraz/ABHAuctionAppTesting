//package tests.regression_tests;
//
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.*;
//
//import io.restassured.path.json.JsonPath;
//import org.testng.annotations.Test;
//import test_component.BaseTest;
//
//
//public class RestTest extends BaseTest {
//
//    @Test
//    public void getRandomProduct() {
//        RestAssured.baseURI = "http://localhost:8081/";
//        String response = given().log().all()
//                .when().get("api/v1/products/random")
//                .then().log().all().assertThat().statusCode(200)
//                .extract().response().asString();
//
//        JsonPath js = new JsonPath(response);
//        String idRandomProduct = js.get("id");
//        System.out.println(idRandomProduct);
//    }
//
//}















