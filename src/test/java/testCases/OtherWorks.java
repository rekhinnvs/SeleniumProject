package testCases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class OtherWorks extends Base {
    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(enabled = false)
    public void spiceJetGetCurrency() {
        driver.get("https://www.spicejet.com/");
        Select currency = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        currency.selectByValue("USD");
        System.out.println(currency.getFirstSelectedOption().getText());
    }

    @Test(enabled = false)
    public void selectPassengers() {
        driver.get("https://www.spicejet.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passengers = driver.findElement(By.id("divpaxinfo"));
        wait.until(ExpectedConditions.textToBePresentInElement(passengers, "1 Adult"));
        //wait.until(ExpectedConditions.elementToBeClickable(passengers));
        passengers.click();
        //Select the number of adult passengers.
        Select selectAdult = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));
        selectAdult.selectByValue("4");

        //Select the number of child
        Select selectChild = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Child")));
        selectChild.selectByValue("3");
    }

    @Test(enabled = false)
    public void checkSSLCertificate() {
        driver = initializeCustomDriver();
        driver.get("https://tools.borqsindia.com/");
    }

    @Test(enabled = false)
    public void bigBasketNewWindow() throws InterruptedException {
        driver.get("https://www.bigbasket.com/");
        driver.manage().window().maximize();
        //Thread.sleep(30000);
        WebElement miniDriver = driver.findElement(By.xpath("(//*[@ng-controller='Footer as vm']/div/div[2])[1]"));
        System.out.println(miniDriver.findElements(By.tagName("a")).size());
        int size = miniDriver.findElements(By.tagName("a")).size();
        for (int i = 0; i < size; i++) {
            String Controlenter = Keys.chord(Keys.COMMAND, Keys.ENTER);
            miniDriver.findElements(By.tagName("a")).get(i).sendKeys(Controlenter);
        }
    }

    @Test(enabled = false)
    public void manageCookies() {
        //Maximize the window
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //Delete a cookie with a name.
        driver.manage().deleteCookieNamed("cookieName");
    }

    @Test(description = "Take screenshots")
    public void takeScreenShots() throws IOException {
        driver.get("https://www.bigbasket.com/");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File outPutDirectory = new File("./output");
        //Delete all the files in the directory before copying the new files.
        FileUtils.cleanDirectory(outPutDirectory);
        FileUtils.copyFile(file, new File("./output/screenShot.png"));
    }
}
