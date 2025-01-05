package reporting;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        // Create test in Extent
        ExtentManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogReporter.logInfoOnly("Test Passed");
        ExtentManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogReporter.logError("Test Failed", result.getThrowable());
        // Attach screenshot if desired
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogReporter.logWarn("Test Skipped: " + result.getThrowable());
    }
}
