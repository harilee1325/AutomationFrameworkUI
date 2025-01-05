package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotUtil {

    /**
     * viewportPasting(100): AShot will scroll through the page in increments of 100px, stitching images into a single full-page screenshot.
     * @param driver
     * @return
     */
    public static Screenshot captureFullPage(WebDriver driver) {
        return new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100)) // scroll & stitch
                .takeScreenshot(driver);
    }

    /**
     * captureElement: If you only want a specific element’s screenshot (like a chart, logo, etc.).
     * @param driver
     * @param element
     * @return
     */
    public static Screenshot captureElement(WebDriver driver, WebElement element) {
        return new AShot()
                .takeScreenshot(driver, element);
    }
}
