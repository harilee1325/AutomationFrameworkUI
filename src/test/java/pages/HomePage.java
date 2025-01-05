package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.ExtentManager;
import reporting.LogReporter;

import java.time.Duration;

public class HomePage {


    private WebDriver driver;
    private WebDriverWait wait;

    private By logoHomePage   = By.className("app_logo");
    private By hamburgerIconHomePage = By.id("react-burger-menu-btn");
    private By aboutSideBarHomePage = By.id("about_sidebar_link");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public boolean isOnHomePage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoHomePage)).isDisplayed();
    }

    public void clickHamburgerIcon() {
        LogReporter.logInfo("Clicked on Hamburger button.");
        wait.until(ExpectedConditions.elementToBeClickable(hamburgerIconHomePage)).click();
    }

    public void clickAboutSectionSideLink(){
        LogReporter.logInfo("Clicked on About link.");
        wait.until(ExpectedConditions.elementToBeClickable(aboutSideBarHomePage)).click();
    }

}
