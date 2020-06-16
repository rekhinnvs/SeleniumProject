package testCases;

import objectRepository.PageFacebookLogin;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

public class PageFactory extends Base {

    static WebDriver driver;
    PageFacebookLogin pageFacebookLogin;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        implicitWaitFor(3);
        //Create an object for the pagefactory page for facebook login
        pageFacebookLogin = new PageFacebookLogin(driver);
    }

    @Test
    public void facebookLogin() {
        driver.get("https://facebook.com");
        pageFacebookLogin.userName().sendKeys("dracos");
        pageFacebookLogin.password().sendKeys("password");
        pageFacebookLogin.login().click();
    }
}
