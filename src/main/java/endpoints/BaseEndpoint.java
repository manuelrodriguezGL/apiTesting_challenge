package endpoints;

import constants.EndpointRoutes;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseEndpoint {

    static RequestSpecification requestSpecification;
    private static String base_url = "";

    protected EndpointRoutes endpointRoutes;

    BaseEndpoint(String _base_url) {
        base_url = _base_url;
        endpointRoutes = new EndpointRoutes();
    }

    /**
     * Creates the endpoint path, in cases where it needs more than just the base URL
     *
     * @param path The additional path endpoints
     * @return A String representing the whole endpoint path
     * @throws Exception
     */
    protected static String buildEndpointPath(String path) throws Exception {
        if (base_url.isEmpty())
            throw new Exception("Base URL can't be blanks!");
        return base_url + path;
    }

    /**
     * Performs basic auth on the endpoint, using RestAssured
     *
     * @param usr User
     * @param psw Password
     * @return a RequestSpecification with API information
     */
    public static RequestSpecification authenticate(String usr, String psw) {
        requestSpecification = given().auth().preemptive().basic(usr, psw);
        return requestSpecification;
    }
}
