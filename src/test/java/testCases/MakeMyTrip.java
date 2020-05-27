package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.security.Key;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class MakeMyTrip extends Base{

    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = Base.initializeDriver();
        driver.get("https://www.makemytrip.com/");
    }

    @Test
    public void autoSuggestion() throws InterruptedException {
        driver.findElement(By.id("fromCity")).click();
        implicitWaitFor(2);
        //Find the preceding sibling
        //WebElement sourceText =driver.findElement(By.xpath("//div[@id='react-autowhatever-1']/preceding-sibling::input"));
        WebElement source = driver.findElement(By.xpath("//input[@placeholder='From']"));
        source.sendKeys("MUM");
        Thread.sleep(2000);
        source.sendKeys(Keys.ARROW_DOWN);
        source.sendKeys(Keys.ENTER);

        // Enter text Bangalore to destination search
        WebElement destination = driver.findElement(By.xpath("//input[@placeholder='To']"));
        destination.sendKeys("Bengaluru");
        Thread.sleep(2000);
        destination.sendKeys(Keys.ARROW_DOWN);
        destination.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

    }

    @Test
    public void selectCalendarDate() {
        driver.findElement(By.cssSelector(".DayPicker-Day.DayPicker-Day--today")).click();
        driver.findElement(By.cssSelector(".primaryBtn.font24.latoBold.widgetSearchBtn ")).click();
    }
}
