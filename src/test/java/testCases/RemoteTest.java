package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.net.MalformedURLException;

public class RemoteTest extends Base {

    static WebDriver driver;

    @BeforeClass
    public void initDriver() throws MalformedURLException {
        driver = initializeRemoteDriver();

    }

    @Test
    public void getPage() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        Assert.assertTrue(driver.getTitle().equals("Wikipedia, the free encyclopedia"));
    }
}
