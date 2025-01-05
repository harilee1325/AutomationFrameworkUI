package reporting;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            /**
             * Open Run/Debug Configurations:
             *
             * Go to Run > Edit Configurations in IntelliJ.
             * Add the System Property:
             *
             * Under the VM options field, type:
             * -Denv=QA
             * Click Apply and then Run.
             */
            String environment = System.getProperty("env", "DEV");
            extent.setSystemInfo("Environment", environment);

            String developerName = System.getenv("GIT_USER_NAME");
            if (developerName == null || developerName.isEmpty()) {
                developerName = System.getProperty("user.name", "UnknownUser");
            }
            extent.setSystemInfo("Tester", developerName);        }
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}

