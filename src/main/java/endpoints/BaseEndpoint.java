package endpoints;

import io.restassured.specification.RequestSpecification;
import utils.CommonUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseEndpoint {

    static RequestSpecification requestSpecification;
    static String baseUrl;

    static {
        try {
            baseUrl = CommonUtils.getPropertyValue("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BaseEndpoint() {
    }

    public BaseEndpoint(String psw, String usr) {
        if (requestSpecification == null)
            authenticate(usr, psw);
    }

    protected static String buildEndpointPath(String path) {
        return baseUrl + path;
    }

    public static RequestSpecification authenticate(String usr, String psw) {
        requestSpecification = given().auth().preemptive().basic(usr, psw);
        return requestSpecification;
    }

}
