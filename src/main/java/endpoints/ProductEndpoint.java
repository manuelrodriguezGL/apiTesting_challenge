package endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import constants.EndpointRoutes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Product;
import utils.CommonUtils;

import java.util.List;

public class ProductEndpoint extends BaseEndpoint {
    public Response getProductById(String productId) {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH + "/{id}");
        requestSpecification.pathParams("id", productId);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
    }

    public Response getProductByQuantity(int quantity) {

        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.queryParams("per_page", quantity);

        Response response = requestSpecification.given().when().get(endpointPath);

        return response;

    }

    public Response getProductsSorted(String order, String orderBy) {
        String endpointPath = buildEndpointPath(EndpointRoutes.PRODUCT_PATH);
        requestSpecification.queryParams("order", order, "orderby", orderBy);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
    }

    @SuppressWarnings("unchecked")
    public Response postProduct(String name, String slug, String description) {
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
            throws JsonProcessingException {
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

    public List<Product> getProductList(String order, String orderBy) {
        return getProductsSorted(order, orderBy).jsonPath().getList("", Product.class);
    }
}
