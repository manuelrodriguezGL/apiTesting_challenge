package products;

import dataProviders.ProductDataProvider;
import endpoints.ProductEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testBase.TestBase;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ProductTest implements TestBase {

    private ProductEndpoint productEndpoint;

    @Test(description = "Posts a new product to database",
            groups = {"Products"}, dataProvider = "ProductFaker", dataProviderClass = ProductDataProvider.class,
            priority = 0)
    public void postProduct(String name, String slug, String description) {
        try {
            Response response = productEndpoint.postProduct(name, slug, description);
            response.then()
                    .assertThat().statusCode(201)
                    .body("name", equalTo(name))
                    .body("slug", equalTo(slug.toLowerCase()))
                    .body("description", equalTo(description))
                    .log().all();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Gets a predefined number of products", groups = {"Products"}, priority = 1)
    @Parameters({"product_quantity"})
    public void getProductsByQuantity(String quantity) {
        try {
            Response response = productEndpoint.getProductByQuantity(Integer.parseInt(quantity));
            response.then()
                    .assertThat().statusCode(200)
                    .body("id", hasSize(Integer.parseInt(quantity)))
                    .log().all();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Puts a new description into existing product",
            groups = "Products", dataProvider = "ProductDescription", dataProviderClass = ProductDataProvider.class,
            priority = 1)
    public void putProductDescription(String productId, String name, String slug, String description) {
        try {

            /*
                Given that we are using a test environment, the data could be deleted.
                That's why tests have an execution priority, and we are getting here the last added ID
                based on a parameter from properties file
             */
            if (Boolean.parseBoolean(commonUtils.getPropertyValue("DATA_OVERRIDE")))
                productId = productEndpoint.getLastProductID(commonUtils.getPropertyValue("ORDER"));

            // To have a different description on every run
            description = description + Math.random();
            Response response = productEndpoint.putProductDescription(productId, name, slug, description);
            response.then()
                    .assertThat().statusCode(200)
                    .body("description", equalTo(description))
                    .log().all();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(description = "Patches an existing product with a new name",
            groups = "Products", dataProvider = "ProductName", dataProviderClass = ProductDataProvider.class,
            priority = 1)
    public void patchProductName(String productId, String productName) {
        try {
            /*
                Given that we are using a test environment, the data could be deleted.
                That's why tests have an execution priority, and we are getting here the last added ID
                based on a parameter from properties file
             */
            if (Boolean.parseBoolean(commonUtils.getPropertyValue("DATA_OVERRIDE")))
                productId = productEndpoint.getLastProductID(commonUtils.getPropertyValue("ORDER"));

            Response response = productEndpoint.patchProductName(productId, productName);
            response.then()
                    .assertThat().statusCode(200)
                    .body("name", equalTo(productName))
                    .log().all();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * This test searches for the last product added. If none, then fails with an exception.
     * Notice the hardcoded value for order parameter. This is in purpose, so we get the last item always
     * and avoid mistakes
     * At the end, the test validates if the product was indeed deleted, by getting that product ID and checking for a
     * 404 response
     *
     * @param order   Order of the sorted product list. Default to Descending
     * @param orderBy Criteria to order the list of products
     */
    @Test(description = "Deletes the last added product",
            groups = "Products", priority = 2)
    @Parameters({"order", "orderBy"})
    public void deleteLastProduct(String order, String orderBy) {
        try {
            String lastProductId = productEndpoint.getLastProductID(commonUtils.getPropertyValue("ORDER"));

            Response response = productEndpoint.deleteProductById(lastProductId);
            response.then()
                    .assertThat().statusCode(200)
                    .log().all();

            Response deletedResponse = productEndpoint.getProductById(lastProductId);
            deletedResponse.then()
                    .assertThat().statusCode(404);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true,
            description = "Setup the customer endpoint to make the requests")
    @Parameters({"baseUrl", "product_path", "api_user", "api_psw"})
    public void testSetup(String baseUrl, String customerPath, String usr, String psw) {
        productEndpoint = new ProductEndpoint(baseUrl + customerPath);
        productEndpoint.authenticate(usr, psw);
    }
}
