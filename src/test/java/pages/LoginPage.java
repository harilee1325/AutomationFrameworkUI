package pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.ExtentManager;
import reporting.LogReporter;
import utils.LoggerUtil;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);


    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton   = By.id("login-button");
    private By loginHeader   = By.className("login_logo");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    public boolean isOnLoginPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
    }

    public void enterUsername(String username) {
        LogReporter.logInfo("Entered username: " + username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        LogReporter.logInfo("Entered password.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        LogReporter.logInfo("Clicked on login button.");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }


    public void redirectToLogin(String baseUrl) {
        driver.get(baseUrl);

    }
}
