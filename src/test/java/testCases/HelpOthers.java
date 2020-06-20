package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.List;

public class HelpOthers extends Base {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = initializeDriver();
        implicitWaitFor(3);
    }

    @Test(enabled = false)
    public void clearTripFlightBooking() throws InterruptedException {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        String Country = "Deline, CA - Deline (YWJ)", text = "";
        int count = 0;
        driver.findElement(By.id("FromTag")).click();
        while (!text.equalsIgnoreCase(Country)) {
            count++;
            driver.findElement(By.id("FromTag")).sendKeys("del");
            Thread.sleep(3000);
            for (int i = 0; i < count; i++)
                driver.findElement(By.id("FromTag")).sendKeys(Keys.DOWN);
            Thread.sleep(1000);
            driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);
            text = driver.findElement(By.id("FromTag")).getAttribute("value");
            System.out.println(text);
            driver.findElement(By.id("FromTag")).clear();
            if (count > 10)
                break;
        }
        if (count > 10)
            System.out.println("Element not found");
        else
            System.out.println("Element found");
    }

    @Test
    public void clearTrip() throws InterruptedException {
        driver.get("https://www.cleartrip.com/");
        driver.findElement(By.id("FromTag")).sendKeys("del");
        Thread.sleep(5000);
        WebElement dropDown = driver.findElement(By.id("ui-id-1"));
        //List of cities which is populated after the search
        List<WebElement> dropDownItems = dropDown.findElements(By.className("list"));
        for (WebElement city : dropDownItems) {
            if (city.getText().contains("Deline")) {
                city.click();
                break;
            } else
                System.out.println("Search city is not found");
        }

    }
}
