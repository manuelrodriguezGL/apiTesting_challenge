package endpoints;

import constants.EndpointRoutes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Product;
import utils.CommonUtils;

import java.util.List;

public class ProductEndpoint extends BaseEndpoint {

    public ProductEndpoint(String baseUrl) {
        super(baseUrl);
    }

    /**
     * Gets a Product by ID
     *
     * @param productId Product ID
     * @return An HTTP Response object with product inside a JSON
     * @throws Exception
     */
    public Response getProductById(String productId) throws Exception {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
    }

    /**
     * Gets a subset of all Products from API, using default API sorting
     *
     * @param quantity Quantity of products to get
     * @return An HTTP Response object with all products inside a JSON
     * @throws Exception
     */
    public Response getProductByQuantity(int quantity) throws Exception {

        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.queryParams("per_page", quantity);

        Response response = requestSpecification.given().when().get(endpointPath);

        return response;

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
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.queryParams("order", order, "orderby", orderBy);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
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
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);

        // The API expects URL encoded data. Since JSON library doesn't have a way to serialize that,
        // I delegate that into RestAssured, which needs an Object to build the form params
        requestSpecification.formParams(CommonUtils.objectToMap(
                new Product("", name, slug, description)));

        Response response = requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(endpointPath);

        return response;
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
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);
        requestSpecification.body(CommonUtils.objectToJsonString(new Product(productId, name, slug, description),
                "%s"));

        Response response = requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .put(endpointPath);

        return response;
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
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);
        requestSpecification.body(CommonUtils.objectToJsonString(new Product(productId, name, "", ""),
                "%s"));

        Response response = requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .patch(endpointPath);

        return response;
    }

    /**
     * Forces the deletion of a product by ID
     *
     * @param productId Product ID
     * @return An HTTP Response object with all product data inside a JSON
     * @throws Exception
     */
    public Response deleteProductById(String productId) throws Exception {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);

        // We force the deletion of the resource, as required by the API
        requestSpecification.queryParams("force", Boolean.TRUE.toString());

        Response response = requestSpecification.given().when().delete(endpointPath);
        return response;
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
        return getProductsSorted(order, orderBy).jsonPath().getList("", Product.class);
    }

    /**
     * Gets the last added product
     *
     * @param order   Order of sort criteria: ascending or descending
     * @param orderBy Order by criteria: Field to order the products by
     * @return A String with last added product ID
     * @throws Exception
     */
    public String getLastProductID(String order, String orderBy) throws Exception {
        // Since we need the last product, we force the 'desc' value
        List<Product> products = getProductList("desc", orderBy);
        return products.get(0).getId();
    }
}
