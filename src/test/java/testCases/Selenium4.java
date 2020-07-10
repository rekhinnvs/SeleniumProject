package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class Selenium4 extends Base {
    WebDriver driver;


    @BeforeClass
    public void setup(ITestContext context) {
        driver = initializeDriver();
        implicitWaitFor(3);
        context.setAttribute("WebDriver", driver);
    }

    @Test
    public void aboveOfElement() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        System.out.println(driver.findElement(withTagName("label").above(nameEditBox)).getText());
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
