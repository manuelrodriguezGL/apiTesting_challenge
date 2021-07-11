package customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataProviders.CustomerDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import payload.Customer;
import testBase.TestBase;

import java.util.Map;

public class CustomerTest extends TestBase {

    private static final String PATH = "/customers";
    private static final String CONTEXT = "view";

    @Test(description = "Get Customers by ID", groups = {"Excluded"})
    @Parameters({"customerId"})
    public static void getCustomers(String customerId) {
        SoftAssert softAssert = new SoftAssert();

        String requestPath = baseUrl + PATH + "/{id}";

        requestSpecification.pathParams("id", customerId);
        requestSpecification.queryParams("context", CONTEXT);

        Response response = requestSpecification.given().when().get(requestPath);

        response.then().log().all();

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().get("id").toString(), customerId);
        softAssert.assertAll(String.format("%s %s %s",
                GLOBAL_TEST_FAILED_MESSAGE, "Could not find customer with ID :", customerId));
    }

    @Test(description = "Post a new customer to database",
            groups = {"Excluded"}, dataProvider = "Customer", dataProviderClass = CustomerDataProvider.class)
    public static void postCustomer(String email, String first_name, String last_name, String username,
                                    String password) {
        String requestPath = baseUrl + PATH;
        requestSpecification.queryParams("context", CONTEXT);
        requestSpecification.formParams(fillCustomerData(email, first_name, last_name, username, password));

        Response response = requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(requestPath);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 201,
                String.format("%s %s %s",
                        GLOBAL_TEST_FAILED_MESSAGE, "Could not find customer with username :", username));
    }

    @Test(description = "Puts a new billing address into customer",
    groups = "Customers",  dataProvider = "Customer", dataProviderClass = CustomerDataProvider.class)

    /**
     * Creates a Customer object with parameters, and returns a Map of values, that is going to be used as
     * request form parameters
     *
     * @param email      Customer email
     * @param first_name Customer first name
     * @param last_name  Customer last name
     * @param username   Customer username
     * @param password   Customer password
     * @return A Map object with all values
     */
    private static Map fillCustomerData(String email, String first_name, String last_name, String username,
                                        String password) {
        Customer customer = new Customer(email, first_name, last_name, username, password);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(customer, Map.class);
    }
}

