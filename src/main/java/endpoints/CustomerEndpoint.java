package endpoints;

import io.restassured.response.Response;
import utils.CommonUtils;

import java.io.IOException;

public class CustomerEndpoint extends BaseEndpoint {

    public Response getCustomer(String id) throws IOException {

        String endpointPath = buildEndpointPath(CommonUtils.getPropertyValue("customer_path") + "/{id}");
        requestSpecification.pathParams("id", id);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;

    }

    public Response postCustomer(String email, String first_name, String last_name, String username,
                                 String password)
    {

    }

}
