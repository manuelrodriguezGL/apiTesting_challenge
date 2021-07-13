package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Customer;
import utils.CommonUtils;

import java.io.IOException;

public class CustomerEndpoint extends BaseEndpoint {

    private static String CUSTOMER_PATH = null;

    static {
        try {
            CUSTOMER_PATH = CommonUtils.getPropertyValue("customer_path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Response getCustomer(String id) throws IOException {

        String endpointPath = buildEndpointPath(CUSTOMER_PATH + "/{id}");
        requestSpecification.pathParams("id", id);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;

    }

    @SuppressWarnings("unchecked")
    public Response postCustomer(String email, String first_name, String last_name, String username,
                                 String password) {
        String endpointPath = buildEndpointPath(CUSTOMER_PATH);
        requestSpecification.formParams(CommonUtils.objectToMap(
                new Customer("", email, first_name, last_name, username, password)));

        Response response = requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(endpointPath);

        return response;
    }

}
