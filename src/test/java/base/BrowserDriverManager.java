package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class BrowserDriverManager {

    //Thread local ensures thread safety if we decide to run tests in parallel
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver()
                    .clearDriverCache()
                    .forceDownload()
                    .browserVersion(Constants.CHROMEDRIVER_VERSION).setup();
            driver.set(new ChromeDriver());
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

