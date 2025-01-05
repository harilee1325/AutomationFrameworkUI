package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility {
    public static WebElement findElementWithJs(WebDriver driver, String path) {
        // Using a CSS-based query via JavaScript:
        WebElement elem = driver.findElement(new ByJavaScript(
                path, driver)
        );
        return elem;
    }

    public static WebElement findElementByText(WebDriver driver, String text){

        return driver.findElement(By.xpath(String.format("//*[text()='%s']", text)));

    }

}
