package products;

import dataProviders.ProductDataProvider;
import endpoints.ProductEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testBase.TestBase;

import static org.hamcrest.Matchers.hasSize;

public class ProductTest extends TestBase {

    private static ProductEndpoint productEndpoint;

    @Test(description = "Gets a predefined number of products", groups = {"Products"})
    @Parameters({"product_quantity"})
    public static void getProductsByQuantity(String quantity) {
        try {
            // SoftAssert softAssert = new SoftAssert();
            Response response = productEndpoint.getProductByQuantity(Integer.parseInt(quantity));
            response.then()
                    .assertThat().body("id", hasSize(Integer.parseInt(quantity)))
                    .and()
                    .statusCode(200)
                    .log().all();

//            softAssert.assertEquals(response.body().jsonPath().getList("id").size(), Integer.parseInt(quantity),
//                    String.format("%s %s %s",
//                            GLOBAL_TEST_FAILED_MESSAGE, "Quantity retrieved is different than requested :", quantity));
//            softAssert.assertEquals(response.getStatusCode(), 200);
//            softAssert.assertAll(String.format("%s %s %s",
//                    GLOBAL_TEST_FAILED_MESSAGE, "Could not find products! Quantity requested :", quantity));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Post a new product to database",
            groups = {"Products"}, dataProvider = "ProductFaker", dataProviderClass = ProductDataProvider.class)
    public static void postProduct(String name, String slug, String description) {
        try {
            Response response = productEndpoint.postProduct(name, slug, description);
            response.then()
                    .assertThat().statusCode(201)
                    .log().all();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Puts a new description into existing product",
            groups = "Products", dataProvider = "ProductDescription", dataProviderClass = ProductDataProvider.class)
    public static void putProductDescription(String productId, String name, String slug, String description) {
        try {
            Response response = productEndpoint.putProductDescription(productId, name, slug, description);
            response.then()
                    .assertThat().statusCode(200)
                    .log().all();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Patches an existing product with a new name",
            groups = "Products", dataProvider = "ProductName", dataProviderClass = ProductDataProvider.class)
    public static void patchProductName(String productId, String productName) {
        try {
            Response response = productEndpoint.patchProductName(productId, productName);
            response.then()
                    .assertThat().statusCode(200)
                    .log().all();
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
