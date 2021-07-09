package customers;

import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import payload.Customer;
import testBase.TestBase;

public class CustomerTest extends TestBase {


    @Test(description = "Get Customers by ID", groups = {"Customers"})
    @Parameters({"path", "viewContext", "customerId"})
    public static void getCustomers(String path, String context, String customerId) {
        SoftAssert softAssert = new SoftAssert();

        String requestPath = baseUrl + path + "/{id}";

        requestSpecification.pathParams("id", customerId);
        requestSpecification.queryParams("context", context);

        Response response = requestSpecification.given().when().get(requestPath);

        System.out.println("Response body: " + "\n" + response.asPrettyString());

        Customer customer = response.as(Customer.class);

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().get("id").toString(), customerId);
        softAssert.assertAll(String.format("%s %s %s",
                GLOBAL_TEST_FAILED_MESSAGE, "Could not find customer with ID :", customerId));
    }

}
