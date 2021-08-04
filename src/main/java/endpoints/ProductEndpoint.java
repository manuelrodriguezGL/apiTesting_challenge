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

    public Response getProductById(String productId) throws Exception {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
    }

    public Response getProductByQuantity(int quantity) throws Exception {

        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.queryParams("per_page", quantity);

        Response response = requestSpecification.given().when().get(endpointPath);

        return response;

    }

    public Response getProductsSorted(String order, String orderBy) throws Exception {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.queryParams("order", order, "orderby", orderBy);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
    }

    @SuppressWarnings("unchecked")
    public Response postProduct(String name, String slug, String description) throws Exception {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.formParams(CommonUtils.objectToMap(
                new Product("", name, slug, description)));

        Response response = requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(endpointPath);

        return response;
    }

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

    public Response deleteProductById(String productId) throws Exception {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);

        // We force the deletion of the resource, as required by the API
        requestSpecification.queryParams("force", Boolean.TRUE.toString());

        Response response = requestSpecification.given().when().delete(endpointPath);
        return response;
    }

    public List<Product> getProductList(String order, String orderBy) throws Exception {
        return getProductsSorted(order, orderBy).jsonPath().getList("", Product.class);
    }

    public String getLastProductID(String order, String orderBy) throws Exception {
        List<Product> customers = getProductList("desc", orderBy);
        return customers.get(0).getId();
    }
}
