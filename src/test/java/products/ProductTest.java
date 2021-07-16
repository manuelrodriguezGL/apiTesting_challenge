package products;

import endpoints.ProductEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;

public class ProductTest extends TestBase {

    private static ProductEndpoint productEndpoint;

    @Test(description = "Gets a predefined number of products", groups = {"Products"})
    @Parameters({"product_quantity"})
    public static void getProductsByQuantity(String quantity) {
        try {
            SoftAssert softAssert = new SoftAssert();
            Response response = productEndpoint.getProductByQuantity(Integer.parseInt(quantity));
            response.then().log().all();

            softAssert.assertEquals(response.body().jsonPath().getList("id").size(), Integer.parseInt(quantity),
                    String.format("%s %s %s",
                            GLOBAL_TEST_FAILED_MESSAGE, "Quantity retrieved is different than requested :", quantity));
            softAssert.assertEquals(response.getStatusCode(), 200);
            softAssert.assertAll(String.format("%s %s %s",
                    GLOBAL_TEST_FAILED_MESSAGE, "Could not find products! Quantity requested :", quantity));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true,
            description = "Setup the product endpoint to make the requests")
    public void testSetup() {
        productEndpoint = new ProductEndpoint();
    }
}
