package testBase;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static io.restassured.RestAssured.given;

@Listeners(TestExecutionListener.class)
public class TestBase {

    protected static RequestSpecification requestSpecification;
    protected static String baseUrl = "http://52.14.147.231/wp-json/wc/v2";
    protected static final String  GLOBAL_TEST_FAILED_MESSAGE = "Test execution failed! Message: \n";

    @BeforeMethod(alwaysRun = true)
    @Parameters({"validUser", "validPassword"})
    public static void authenticate(String usr, String psw) {
        RestAssured.baseURI = baseUrl;
        requestSpecification = given().auth().preemptive().basic(usr, psw);
    }

    public void login(String usr, String psw) {
    }
}
