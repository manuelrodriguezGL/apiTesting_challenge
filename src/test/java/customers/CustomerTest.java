package customers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataProviders.CustomerDataProvider;
import endpoints.CustomerEndpoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import payload.BillingAddress;
import payload.Customer;
import testBase.TestBase;

import java.io.IOException;
import java.util.Map;

public class CustomerTest extends TestBase {

    private static final String PATH = "/customers";
    private static final String CONTEXT = "view";
    private static CustomerEndpoint customerEndpoint;

    @BeforeMethod(alwaysRun = true)
    public void testSetup(){
        customerEndpoint = new CustomerEndpoint();
    }

    @Test(description = "Get Customers by ID", groups = {"Customers"})
    @Parameters({"customerId"})
    public static void getCustomers(String customerId) throws IOException {
        SoftAssert softAssert = new SoftAssert();

        Response response = customerEndpoint.getCustomer(customerId);
        response.then().log().all();

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().get("id").toString(), customerId);
        softAssert.assertAll(String.format("%s %s %s",
                GLOBAL_TEST_FAILED_MESSAGE, "Could not find customer with ID :", customerId));
    }

    @Test(description = "Post a new customer to database",
            groups = {"Excluded"}, dataProvider = "CustomerFaker", dataProviderClass = CustomerDataProvider.class)
    public static void postCustomer(String email, String first_name, String last_name, String username,
                                    String password) {
        //TODO change the data provider. Leave the existing one, but rename to CustomerExcel
        // Create another one, CustomerArray, and use Faker library
        String requestPath = baseUrl + PATH;
        requestSpecification.formParams(fillCustomerData("", email, first_name, last_name, username, password));

        Response response = requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(requestPath);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 201,
                String.format("%s %s %s",
                        GLOBAL_TEST_FAILED_MESSAGE, "Could not find customer with username :", username));
    }

    @Test(description = "Puts a new billing address into existing customer",
            groups = "Excluded", dataProvider = "BillingAddress", dataProviderClass = CustomerDataProvider.class)
    public static void putCustomerBillingAddress(String customerId, String first_name, String last_name, String company,
                                                 String address_1, String address_2, String city, String state,
                                                 String postcode, String country, String email, String phone) throws JsonProcessingException {

        String requestPath = baseUrl + PATH + "/{id}";
        requestSpecification.pathParams("id", customerId);
        requestSpecification.body(fillBillingAddressData(first_name, last_name, company, address_1, address_2,
                city, state, postcode, country, email, phone));

        Response response = requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .put(requestPath);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200,
                String.format("%s %s %s",
                        GLOBAL_TEST_FAILED_MESSAGE, "Could not PUT billing address for customer ID :", customerId));
    }

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
    private static Map fillCustomerData(String customerId, String email, String first_name, String last_name,
                                        String username, String password) {
        Customer customer = new Customer(customerId, email, first_name, last_name, username, password);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(customer, Map.class);
    }

    /**
     * Creates a Billing address object, and then maps it into a JSON string, with the format needed for a
     * request body that uses this address
     *
     * @param first_name Customer first name
     * @param last_name  Customer last name
     * @param company    Customer Company
     * @param address_1  Customer address line 1
     * @param address_2  Customer address line 2
     * @param city       Customer city
     * @param state      Customer state
     * @param postcode   Customer postal code
     * @param country    Customer country
     * @param email      Customer email
     * @param phone      Customer phone
     * @return A string representing the billing address request body
     * @throws JsonProcessingException
     */
    private static String fillBillingAddressData(String first_name, String last_name, String company,
                                                 String address_1, String address_2, String city, String state,
                                                 String postcode, String country, String email, String phone)
            throws JsonProcessingException {

        //TODO create an override of toString method on Billing address endpoint
        BillingAddress billingAddress = new BillingAddress(first_name, last_name, company, address_1, address_2,
                city, state, postcode, country, email, phone);
        ObjectMapper objectMapper = new ObjectMapper();
        return "{ \"billing\" : " + objectMapper.writeValueAsString(billingAddress) + "}";
    }
}

