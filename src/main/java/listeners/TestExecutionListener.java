package listeners;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestExecutionListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult testResult) {
        super.onTestStart(testResult);
        Reporter.log("Executing the following test: " + testResult.getName() + "\n");
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        Reporter.log("The following test has failed: " + testResult.getName() + "\n");
        Reporter.log("Cause of test failure: " + testResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Reporter.log("The following test has passed: " + testResult.getName() + "\n");
    }
}

