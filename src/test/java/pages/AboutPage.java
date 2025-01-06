package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.Screenshot;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AboutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //JS Path
    private String testItAllBtJSPath = "document.querySelector(\"#__next > div.MuiBox-root.css-14ifkx6 > div.MuiContainer-root.MuiContainer-maxWidthLg.css-w03l51 > div > div > div.MuiStack-root.css-lu2zz1 > div > div.MuiStack-root.css-chbenk > div:nth-child(1) > a > button\")";

    //Text
    private String testItAllText = "Test it all. Free";

    public AboutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean verifyTestItAllBtDesign() throws IOException {
        Screenshot currentScreenshot = ScreenshotUtil.captureFullPage(driver);

        File baseline = new File("baselineimages/test_it_all_bt.png");
        if (!baseline.exists()){
            BaselineManager.storeBaseline(currentScreenshot, "test_it_all_bt");
            baseline = new File("baselineimages/test_it_all_bt.png");
        }
        boolean matches = ImageComparisonUtil.compareImages(currentScreenshot, baseline, 0.5);

        return matches;
    }

}
