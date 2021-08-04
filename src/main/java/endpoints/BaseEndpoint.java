package endpoints;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseEndpoint {

    static RequestSpecification requestSpecification;
    private static String base_url = "";

    BaseEndpoint() {
    }

    BaseEndpoint(String _base_url) {
        base_url = _base_url;
    }

    public BaseEndpoint(String psw, String usr) {
        if (requestSpecification == null)
            authenticate(usr, psw);
    }

    protected static String buildEndpointPath(String path) throws Exception {
        if (base_url.isEmpty())
            throw new Exception("Base URL can't be blanks!");
        return base_url + path;
    }

    public static RequestSpecification authenticate(String usr, String psw) {
        requestSpecification = given().auth().preemptive().basic(usr, psw);
        return requestSpecification;
    }

    public void setBaseUrl(String _base_url) {
        base_url = _base_url;
    }

}
