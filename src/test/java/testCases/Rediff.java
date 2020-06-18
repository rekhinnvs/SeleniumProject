package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

public class Rediff extends Base {
    WebDriver driver;

    @BeforeClass
    public void intiDriver() {
        driver = initializeDriver();
        driver.get("http://rediff.com");
    }

    @Test
    public void login() {
        driver.findElement(By.cssSelector("a[title*='Sign in']")).click();
        driver.findElement(By.xpath("//input[contains(@id,'login')]")).sendKeys("userName");
        driver.findElement(By.xpath("//input[contains(@name,'pass')]")).sendKeys("passWord");
        driver.findElement(By.cssSelector("input[value*='Sign']")).click();
    }

}
