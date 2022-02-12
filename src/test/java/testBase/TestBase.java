package testBase;

import endpoints.BaseEndpoint;
import io.restassured.specification.RequestSpecification;
import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(TestExecutionListener.class)
public class TestBase {

    protected final String ORDER = "desc";
    protected final String GLOBAL_TEST_FAILED_MESSAGE = "Test execution failed! Message: \n";
    protected RequestSpecification requestSpecification;

    protected String baseUrl = "";

    @BeforeMethod(alwaysRun = true)
    @Parameters({"baseUrl", "api_user", "api_psw"})
    public void authenticate(String _baseUrl, String usr, String psw) {
        baseUrl = _baseUrl;
        BaseEndpoint baseEndpoint = new BaseEndpoint(_baseUrl);
        requestSpecification = baseEndpoint.authenticate(usr, psw);
    }
}
