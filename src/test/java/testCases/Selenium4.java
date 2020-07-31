package testCases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class Selenium4 extends Base {
    WebDriver driver;


    @BeforeClass
    public void setup(ITestContext context) {
        driver = initializeDriver();
        implicitWaitFor(3);
        context.setAttribute("WebDriver", driver);
    }

    @Test(enabled = false)
    public void aboveOfElement() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        System.out.println(driver.findElement(withTagName("label").above(nameEditBox)).getText());
    }

    @Test(enabled = false, description = "Switch to a tab and get a value from it")
    public void newTab() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement name = driver.findElement(By.name("name"));
        //This is to tell the driver that new windows is a tab.
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iter = windowHandles.iterator();
        String parentWindow = iter.next();
        String childWindow = iter.next();
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/#/index");
        int numberOfLinks = driver.findElements(By.tagName("a")).size();
        System.out.println(numberOfLinks);
        driver.switchTo().window(parentWindow);
        name.sendKeys("" + numberOfLinks);
    }

    @Test
    public void getPartialScreenshot() throws IOException {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Rekhin");
        File screenShot = name.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("./output/partial.png"));
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
