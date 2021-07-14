package endpoints;

import constants.EndpointRoutes;
import io.restassured.specification.RequestSpecification;
import utils.CommonUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseEndpoint {

    static RequestSpecification requestSpecification;
    BaseEndpoint() {
    }

    public BaseEndpoint(String psw, String usr) {
        if (requestSpecification == null)
            authenticate(usr, psw);
    }

    protected static String buildEndpointPath(String path) {
        return EndpointRoutes.BASE_URL + path;
    }

    public static RequestSpecification authenticate(String usr, String psw) {
        requestSpecification = given().auth().preemptive().basic(usr, psw);
        return requestSpecification;
    }

}
