package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

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

    @Test(description = "Switch to a tab and get a value from it")
    public void newTab() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement name = driver.findElement(By.name("name"));
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

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
