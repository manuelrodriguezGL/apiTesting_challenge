package testBase;

import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(TestExecutionListener.class)
public interface TestBase {

    String ORDER = "desc";
    String GLOBAL_TEST_FAILED_MESSAGE = "Test execution failed! Message: \n";

    @BeforeMethod(alwaysRun = true)
    @Parameters({"baseUrl", "api_user", "api_psw"})
    void testSetup(String _baseUrl, String usr, String psw);
}
