package customers;

import dataProviders.CustomerDataProvider;
import endpoints.CustomerEndpoint;
import io.restassured.response.Response;
import org.hamcrest.collection.IsCollectionWithSize;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;

public class CustomerTest extends TestBase {

    private static CustomerEndpoint customerEndpoint;

    @Test(description = "Get Customers by ID", groups = {"Customers"})
    @Parameters({"customerId"})
    public static void getCustomersByID(String customerId) {
        try {
            SoftAssert softAssert = new SoftAssert();

            Response response = customerEndpoint.getCustomerByID(customerId);
            response.then().log().all();

            softAssert.assertEquals(response.getStatusCode(), 200);
            softAssert.assertEquals(response.jsonPath().get("id").toString(), customerId);
            softAssert.assertAll(String.format("%s %s %s",
                    GLOBAL_TEST_FAILED_MESSAGE, "Could not find customer with ID :", customerId));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Get Customers by quantity", groups = {"debug"})
    @Parameters({"quantity", "userRole"})
    public static void getCustomersByQuantity(String quantity, String userRole) {
        try {
            SoftAssert softAssert = new SoftAssert();

            Response response = customerEndpoint.getCustomerByQuantity(Integer.parseInt(quantity), userRole);
            response.then().log().all();
            response.then().assertThat().body("id", IsCollectionWithSize.hasSize(1));
            System.out.println("Size: " + response.body().jsonPath().getList("id").size());

            softAssert.assertEquals(response.body().jsonPath().getList("id").size(), Integer.parseInt(quantity),
                    String.format("%s %s %s",
                            GLOBAL_TEST_FAILED_MESSAGE, "Quantity retrieved is different than requested :", quantity));
            softAssert.assertEquals(response.getStatusCode(), 200);
            softAssert.assertAll(String.format("%s %s %s",
                    GLOBAL_TEST_FAILED_MESSAGE, "Could not find customers! Quantity requested :", quantity));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Post a new customer to database",
            groups = {"Customers"}, dataProvider = "CustomerFaker", dataProviderClass = CustomerDataProvider.class)
    public static void postCustomer(String email, String first_name, String last_name, String username,
                                    String password) {

        try {
            Response response = customerEndpoint.postCustomer(email, first_name, last_name, username, password);
            response.then().log().all();

            Assert.assertEquals(response.statusCode(), 201,
                    String.format("%s %s %s",
                            GLOBAL_TEST_FAILED_MESSAGE, "Could not create customer with username :", username));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test(description = "Puts a new billing address into existing customer",
            groups = "Customers", dataProvider = "BillingAddress", dataProviderClass = CustomerDataProvider.class)
    public static void putCustomerBillingAddress(String customerId, String first_name, String last_name, String company,
                                                 String address_1, String address_2, String city, String state,
                                                 String postcode, String country, String email, String phone) {

        try {

            Response response = customerEndpoint.putCustomerBillingAddress(customerId, first_name, last_name, company,
                    address_1, address_2, city, state, postcode, country, email, phone);
            response.then().log().all();

            Assert.assertEquals(response.statusCode(), 200,
                    String.format("%s %s %s",
                            GLOBAL_TEST_FAILED_MESSAGE, "Could not PUT billing address for customer ID :", customerId));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Patches an existing customer with a new shipping address",
            groups = "Customers", dataProvider = "ShippingAddress", dataProviderClass = CustomerDataProvider.class)
    public static void patchCustomerShippingAddress(String customerId, String first_name, String last_name, String company,
                                                    String address_1, String address_2, String city, String state,
                                                    String postcode, String country) {

        try {

            Response response = customerEndpoint.patchCustomerShippingAddress(customerId, first_name, last_name, company,
                    address_1, address_2, city, postcode, country, state);
            response.then().log().all();

            Assert.assertEquals(response.statusCode(), 200,
                    String.format("%s %s %s",
                            GLOBAL_TEST_FAILED_MESSAGE, "Could not PATCH shipping address for customer ID :", customerId));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true,
            description = "Setup the customer endpoint to make the requests")
    public void testSetup() {
        customerEndpoint = new CustomerEndpoint();
    }
}

