package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import reporting.ExtentManager;
import utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTestLogin {
    protected static Properties config;
    protected WebDriver driver;
    private LoginPage loginPage;

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

    @BeforeClass
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

    @BeforeMethod
    public void loginIfNeeded() {
        // Check if already logged in (e.g., looking for a specific element)
        if (isOnLoginPage()) {
            // Perform the login steps

            loginPage.enterUsername(config.getProperty(Constants.USERNAME).toString());
            loginPage.enterPassword(config.getProperty(Constants.PASSWORD).toString());
            loginPage.clickLogin();

        }
    }

    private boolean isOnLoginPage() {
        // Implement logic to detect if user is logged in,
        // e.g. looking for a logout link, user profile icon, etc.
        loginPage = new LoginPage(driver);
        loginPage.redirectToLogin(config.get(Constants.BASEURL).toString());
        return loginPage.isOnLoginPage();
    }

}
