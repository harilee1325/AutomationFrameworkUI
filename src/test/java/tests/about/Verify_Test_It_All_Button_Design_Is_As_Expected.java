package tests.about;

import base.BaseTest;
import base.BaseTestLogin;
import com.beust.ah.A;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.HomePage;
import reporting.ExtentManager;
import tests.auth.Verify_Successful_Login;

import java.io.IOException;

public class Verify_Test_It_All_Button_Design_Is_As_Expected extends BaseTestLogin {

    @Test
    public void testTestItAllButtonDesign() throws IOException {
        ExtentManager.createTest("testTestItAllButtonDesign");

        HomePage homePage = new HomePage(driver);
        AboutPage aboutPage = new AboutPage(driver);

        homePage.clickHamburgerIcon();
        homePage.clickAboutSectionSideLink();

        aboutPage.verifyTestItAllBtDesign();

    }
}
