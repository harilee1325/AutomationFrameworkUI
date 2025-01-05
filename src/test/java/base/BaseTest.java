package base;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import reporting.ExtentManager;
import utils.Constants;

import java.io.*;
import java.util.Properties;

/**
 * This is the parent class for all tests. It handles:
 *
 * Test setup/teardown
 * Reading from properties files
 * Screenshot capturing on failure
 * Starting/stopping ExtentReports
 */
public class BaseTest {
    protected static Properties config;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports
        ExtentManager.initReports();
    }

    @AfterSuite
    public void afterSuite() {
        // Flush ExtentReports
        ExtentManager.flushReports();
    }

    @BeforeClass
    public void loadConfig() {
        config = new Properties();
        String envFile = "";
        //We load dev.properties in @BeforeClass to ensure it’s ready for all tests in that class.
        if (System.getProperty("env").equals("DEV")){
            envFile = "src/test/resources/config/dev.properties"; // or pass via system property
        }else if (System.getProperty("env").equals("PROD")){
            envFile = "src/test/resources/config/prod.properties"; // or pass via system property
        }

        try (InputStream input = new FileInputStream(envFile)) {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod
    public void setUp() {
        driver = BrowserDriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot if test failed
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            //We take screenshots on test failure and attach them to ExtentReports.
            // Build path for screenshot
            String screenshotPath = "screenshots/"
                    + result.getMethod().getMethodName() + "_"
                    + System.currentTimeMillis() + ".png";

            File destination = new File(screenshotPath);
            source.renameTo(destination);

            // Attach screenshot to ExtentReports
            ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
        }
        BrowserDriverManager.quitDriver();
    }

}


