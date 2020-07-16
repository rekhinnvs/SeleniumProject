package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
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

    @Test(enabled = false)
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

    @Test(enabled = false)
    public void cricBuzzTables() {
        driver.get("https://www.cricbuzz.com/live-cricket-scorecard/27007/bcbxi-vs-zim-2-day-practice-match-zimbabwe-tour-of-bangladesh-2020");
        WebElement table = driver.findElement(By.xpath("//div[@id='innings_1']/div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]"));
        int rowcount = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms']")).size();
    }

    @Test
    public void spiceJectCheckOriginCity() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        Thread.sleep(5000);
        WebElement source = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        source.click();
        driver.findElement(By.xpath("//a[@value='AMD']")).click();
        System.out.println(source.getAttribute("value"));
        Assert.assertTrue(source.getAttribute("value").contains("AMD"));
        System.out.println("AMD Selected from drop down");
    }
}
