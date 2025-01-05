package tests.auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import reporting.ExtentManager;
import utils.Constants;

public class Verify_Successful_Login extends BaseTest {

    @Test
    public void testSuccessLogin() {
        ExtentManager.createTest("testSuccessfulLogin");

        String baseUrl = config.getProperty(Constants.BASEURL);
        String username = config.getProperty(Constants.USERNAME);
        String password = config.getProperty(Constants.PASSWORD);

        driver.get(baseUrl);
        ExtentManager.getTest().info("Navigated to: " + baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);


        // Validate we are on login page
        boolean isLoginPage = loginPage.isOnLoginPage();
        Assert.assertTrue(isLoginPage, "Login page should be displayed.");
        // Enter credentials
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        // Click login
        loginPage.clickLogin();
        // (Add additional steps for post-login assertions if needed)
        // e.g., Verify user is redirected to a dashboard, check some elements, etc.
        boolean isHomePage = homePage.isOnHomePage();
        Assert.assertTrue(isHomePage, "Assertion to represent successful login flow.");
    }


}
