package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Product;

import java.util.List;

public class ProductEndpoint extends BaseEndpoint {

    private String endpointPath;

    public ProductEndpoint(String baseUrl) {
        super(baseUrl);
        endpointPath = baseUrl;
    }

    /**
     * Gets a Product by ID
     *
     * @param productId Product ID
     * @return An HTTP Response object with product inside a JSON
     * @throws Exception
     */
    public Response getProductById(String productId) throws Exception {
        requestSpecification.pathParams("id", productId);
        return requestSpecification.given().when().get(endpointPath + "/{id}");
    }

    /**
     * Gets a subset of all Products from API, using default API sorting
     *
     * @param quantity Quantity of products to get
     * @return An HTTP Response object with all products inside a JSON
     * @throws Exception
     */
    public Response getProductByQuantity(int quantity) throws Exception {
        requestSpecification.queryParams("per_page", quantity);
        return requestSpecification.given().when().get(endpointPath);
    }

    /**
     * Gets all products from API, following an order (ascending or descending), and a order by criteria
     *
     * @param order   Order of sort criteria: ascending or descending
     * @param orderBy Order by criteria: Field to order the products by
     * @return An HTTP Response object with all products inside a JSON
     * @throws Exception
     */
    public Response getProductsSorted(String order, String orderBy) throws Exception {
        requestSpecification.queryParams("order", order, "orderby", orderBy);
        return requestSpecification.given().when().get(endpointPath);
    }

    /**
     * Creates a new product with basic information
     *
     * @param name        Product's name
     * @param slug        Product's slug code
     * @param description Product's description
     * @return An HTTP Response object with all product data inside a JSON
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Response postProduct(String name, String slug, String description) throws Exception {

        // The API expects URL encoded data. Since JSON library doesn't have a way to serialize that,
        // I delegate that into RestAssured, which needs an Object to build the form params
        requestSpecification.formParams(commonUtils.objectToMap(
                new Product("", name, slug, description)));

        return requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(endpointPath);
    }

    /**
     * Updates a product with a new description
     *
     * @param productId
     * @param name
     * @param slug
     * @param description
     * @return An HTTP Response object with all product data inside a JSON
     * @throws Exception
     */
    public Response putProductDescription(String productId, String name, String slug, String description)
            throws Exception {
        requestSpecification.pathParams("id", productId);
        requestSpecification.body(commonUtils.objectToJsonString(new Product(productId, name, slug, description),
                "%s"));

        return requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .put(endpointPath + "/{id}");
    }

    /**
     * Patches existing product name
     *
     * @param productId
     * @param name
     * @return An HTTP Response object with all product data inside a JSON
     * @throws Exception
     */
    public Response patchProductName(String productId, String name) throws Exception {
        requestSpecification.pathParams("id", productId);
        requestSpecification.body(commonUtils.objectToJsonString(new Product(productId, name, "", ""),
                "%s"));

        return requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .patch(endpointPath + "/{id}");
    }

    /**
     * Forces the deletion of a product by ID
     *
     * @param productId Product ID
     * @return An HTTP Response object with all product data inside a JSON
     * @throws Exception
     */
    public Response deleteProductById(String productId) throws Exception {
        requestSpecification.pathParams("id", productId);

        // We force the deletion of the resource, as required by the API
        requestSpecification.queryParams("force", Boolean.TRUE.toString());

        return requestSpecification.given().when().delete(endpointPath + "/{id}");
    }

    /**
     * Returns a sorted list of products
     *
     * @param order   Order of sort criteria: ascending or descending
     * @param orderBy Order by criteria: Field to order the products by
     * @return A sorted list of HTTP Responses with product data inside a JSON
     * @throws Exception
     */
    public List<Product> getProductList(String order, String orderBy) throws Exception {
        return getProductsSorted(order, "id").jsonPath().getList("", Product.class);
    }

    /**
     * Gets the last added product
     *
     * @param order   Order of sort criteria: ascending or descending
     * @return A String with last added product ID
     * @throws Exception
     */
    public String getLastProductID(String order) throws Exception {
        // Since we need the last product, we force the 'desc' value
        List<Product> products = getProductList(order, "");
        return products.get(0).getId();
    }
}
