package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PageFacebookLogin;
import pages.PageFactoryRediffLogin;
import utils.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PageFactory extends Base {

    static WebDriver driver;
    PageFacebookLogin pageFacebookLogin;
    PageFactoryRediffLogin pageRediffLogin;
    Properties properties;

    @BeforeClass
    public void initDriver() throws IOException {
        driver = initializeDriver();
        implicitWaitFor(3);
        properties = new Properties();
        FileInputStream fis = new FileInputStream("./src/data.properties");
        properties.load(fis);
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
        String url = properties.getProperty("rediff");
        System.out.println(url);
        driver.get(url);
        pageRediffLogin = new PageFactoryRediffLogin(driver);
        pageRediffLogin.getLoginButton().click();
        pageRediffLogin.getUserName().sendKeys("username");
        pageRediffLogin.getPassword().sendKeys("password");
        pageRediffLogin.getSignIn().click();
    }

}
