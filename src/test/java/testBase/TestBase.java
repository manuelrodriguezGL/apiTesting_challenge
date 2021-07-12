package testBase;

import endpoints.BaseEndpoint;
import io.restassured.specification.RequestSpecification;
import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(TestExecutionListener.class)
public class TestBase {

    protected static final String GLOBAL_TEST_FAILED_MESSAGE = "Test execution failed! Message: \n";
    protected static RequestSpecification requestSpecification;
    protected static String baseUrl = "http://52.14.147.231/wp-json/wc/v2";

    @BeforeMethod(alwaysRun = true)
    @Parameters({"validUser", "validPassword"})
    public static void authenticate(String usr, String psw) {
        //RestAssured.baseURI = baseUrl;
        requestSpecification = BaseEndpoint.authenticate(usr, psw);
    }
}
