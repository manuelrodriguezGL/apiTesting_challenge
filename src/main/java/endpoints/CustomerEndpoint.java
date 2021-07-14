package endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import constants.EndpointRoutes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.BillingAddress;
import payload.Customer;
import payload.ShippingAddress;
import utils.CommonUtils;

public class CustomerEndpoint extends BaseEndpoint {

    public Response getCustomerByID(String customerId) {

        String endpointPath = buildEndpointPath(EndpointRoutes.CUSTOMER_PATH + "/{id}");
        requestSpecification.pathParams("id", customerId);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;

    }

    public Response getCustomerByQuantity(int quantity, String userRole) {

        String endpointPath = buildEndpointPath(EndpointRoutes.CUSTOMER_PATH);
        requestSpecification.queryParams("per_page", quantity, "role", userRole);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;

    }

    @SuppressWarnings("unchecked")
    public Response postCustomer(String email, String first_name, String last_name, String username,
                                 String password) {
        String endpointPath = buildEndpointPath(EndpointRoutes.CUSTOMER_PATH);
        requestSpecification.formParams(CommonUtils.objectToMap(
                new Customer("", email, first_name, last_name, username, password)));

        Response response = requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(endpointPath);

        return response;
    }

    public Response putCustomerBillingAddress(String customerId, String first_name, String last_name, String company,
                                              String address_1, String address_2, String city, String state,
                                              String postcode, String country, String email, String phone)
            throws JsonProcessingException {
        String endpointPath = buildEndpointPath(EndpointRoutes.CUSTOMER_PATH + "/{id}");
        requestSpecification.pathParams("id", customerId);
        requestSpecification.body(CommonUtils.objectToJsonString(new BillingAddress(first_name, last_name, company,
                        address_1, address_2, city, state, postcode, country, email, phone),
                "{ \"billing\" : %s}"));

        Response response = requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .put(endpointPath);

        return response;
    }

    public Response patchCustomerShippingAddress(String customerId, String first_name, String last_name, String company,
                                                 String address_1, String address_2, String city, String postcode,
                                                 String country, String state)
            throws JsonProcessingException {
        String endpointPath = buildEndpointPath(EndpointRoutes.CUSTOMER_PATH + "/{id}");
        requestSpecification.pathParams("id", customerId);
        requestSpecification.body(CommonUtils.objectToJsonString(new ShippingAddress(first_name, last_name, company,
                        address_1, address_2, city, postcode, country, state),
                "{ \"shipping\" : %s}"));

        Response response = requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .patch(endpointPath);

        return response;
    }
}
