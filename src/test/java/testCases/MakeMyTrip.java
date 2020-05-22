package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class MakeMyTrip {

    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = Base.initializeDriver();
        driver.get("https://www.makemytrip.com/");
    }

    @Test
    public void autoSuggestion() throws InterruptedException {
        driver.findElement(By.id("fromCity")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //Find the preceding sibling
        WebElement sourceText =driver.findElement(By.xpath("//div[@id='react-autowhatever-1']/preceding-sibling::input"));
        sourceText.sendKeys("MUM" + Keys.ARROW_DOWN + Keys.ENTER);

        // Enter text Bangalore to destination search
        Thread.sleep(2000);
        //driver.findElement(By.id("toCity")).click();
        driver.findElement(By.cssSelector("#toCity")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement targetText =driver.findElement(By.xpath("//div[@id='react-autowhatever-1']/preceding-sibling::input"));
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        //targetText.sendKeys("bang");
    }
}
