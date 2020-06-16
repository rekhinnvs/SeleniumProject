package testCases;

import objectRepository.PageFacebookLogin;
import objectRepository.PageFactoryRediffLogin;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

public class PageFactory extends Base {

    static WebDriver driver;
    PageFacebookLogin pageFacebookLogin;
    PageFactoryRediffLogin pageRediffLogin;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        implicitWaitFor(3);
    }

    @Test(description = "Using page objet", enabled = false)
    public void facebookLogin() {
        driver.get("https://facebook.com");
        //Create an object for the pagefactory page for facebook login
        pageFacebookLogin = new PageFacebookLogin(driver);
        pageFacebookLogin.userName().sendKeys("dracos");
        pageFacebookLogin.password().sendKeys("password");
        pageFacebookLogin.login().click();
    }

    @Test(description = "Using pageObject factory")
    public void rediffLogin() {
        driver.get("http://rediff.com");
        pageRediffLogin = new PageFactoryRediffLogin(driver);
        pageRediffLogin.getLoginButton().click();
        pageRediffLogin.getUserName().sendKeys("username");
        pageRediffLogin.getPassword().sendKeys("password");
        pageRediffLogin.getSignIn().click();
    }

}
