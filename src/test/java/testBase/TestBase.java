package testBase;

import listeners.TestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestExecutionListener.class)
public class TestBase {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
    }

    public void login(String usr, String psw) {
    }
}
