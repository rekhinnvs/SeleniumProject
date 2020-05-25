package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utils.Base;

import java.sql.Driver;

public class Assignement2 {

    static WebDriver driver;

    @Test
    public void initDriver() {
        driver = Base.initializeDriver();
        driver.get("https://www.cleartrip.com/");
    }

    @Test
    public void searchForFlights() throws  InterruptedException{
        driver.get("https://www.cleartrip.com/");
        driver.findElement(By.id("DepartDate")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active ")).click();
        Select selectAdults = new Select(driver.findElement(By.id("Adults")));
        selectAdults.selectByValue("3");

        Select selectChildren = new Select(driver.findElement(By.id("Childrens")));
        selectChildren.selectByValue("2");
        driver.findElement(By.id("MoreOptionsLink")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("AirlineAutocomplete")).sendKeys("Indigo");
        driver.findElement(By.id("SearchBtn")).click();
        Thread.sleep(2000);
        String errorMessage = driver.findElement(By.id("homeErrorMessage")).getText();
        System.out.println(errorMessage);

    }
}
