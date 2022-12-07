package testBase;

import endpoints.BaseEndpoint;
import io.restassured.specification.RequestSpecification;
import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import utils.CommonUtils;

@Listeners(TestExecutionListener.class)
public interface TestBase {

    CommonUtils commonUtils = new CommonUtils();

    @BeforeMethod(alwaysRun = true)
    public void testSetup(String _baseUrl, String endpointPath, String usr, String psw);
}
