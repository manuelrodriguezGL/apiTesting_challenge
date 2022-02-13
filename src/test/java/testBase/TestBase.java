package testBase;

import endpoints.BaseEndpoint;
import io.restassured.specification.RequestSpecification;
import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(TestExecutionListener.class)
public interface TestBase {

    String ORDER = "desc";
    String GLOBAL_TEST_FAILED_MESSAGE = "Test execution failed! Message: \n";

    @BeforeMethod(alwaysRun = true)
    public void testSetup(String _baseUrl, String endpointPath, String usr, String psw);
}
