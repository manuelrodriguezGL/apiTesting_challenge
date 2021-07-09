package customers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.TestBase;

import static io.restassured.RestAssured.given;

public class CustomerTest extends TestBase {

    private static RequestSpecification requestSpecification;
    private static String baseUrl = "http://52.14.147.231/wp-json/wc/v2";


    @BeforeClass(alwaysRun = true)
    public static void authenticate() {
        RestAssured.baseURI = baseUrl;

        Header header = new Header("Accept", ContentType.JSON.getAcceptHeader());

        requestSpecification = given().auth().preemptive().basic("auto", "auto");
//        String token = given().auth().preemptive().basic("auto", "auto").toString();
        Response response = requestSpecification.get();


//        System.out.println(token);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(description = "Get Customers", groups = {"Customers"})
    public static void getCustomers() {
        String path = "/customers?context=view";

        requestSpecification = given().auth().preemptive().basic("auto", "auto");
        Response response = requestSpecification.given().when().get(baseUrl + path);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(200, response.getStatusCode());

    }

}
