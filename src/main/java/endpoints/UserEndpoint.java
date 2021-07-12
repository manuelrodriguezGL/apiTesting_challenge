package endpoints;

import io.restassured.response.Response;
import utils.CommonUtils;

import java.io.IOException;

public class UserEndpoint extends BaseEndpoint {

    public Response getCustomer(String id) throws IOException {

        String endpointPath = buildEndpointPath(CommonUtils.getPropertyValue("customer_path") + "/{id}");
        requestSpecification.pathParams("id", id);

        Response response = requestSpecification.given().when().get(endpointPath);
        return response;
        
    }

}
