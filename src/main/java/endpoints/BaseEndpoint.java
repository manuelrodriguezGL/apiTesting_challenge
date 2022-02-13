package endpoints;

import io.restassured.specification.RequestSpecification;
import utils.CommonUtils;

import static io.restassured.RestAssured.given;

public class BaseEndpoint {

    protected final CommonUtils commonUtils;
    protected RequestSpecification requestSpecification;
    private String base_url = "";

    public BaseEndpoint(String _base_url) {
        base_url = _base_url;
        commonUtils = new CommonUtils();
    }

    /**
     * Performs basic auth on the endpoint, using RestAssured
     *
     * @param usr User
     * @param psw Password
     * @return a RequestSpecification with API information
     */
    public RequestSpecification authenticate(String usr, String psw) {
        requestSpecification = given().auth().preemptive().basic(usr, psw);
        return requestSpecification;
    }
}
