package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.concurrent.TimeUnit;

public class RahulFlightBook extends Base {

    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    }

    @Test(description = "Drop down with a select tag")
    public void staticDropDown() {
        WebElement dropDownSelector = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select select = new Select(dropDownSelector);
        select.selectByValue("AED");
        System.out.println("List of currrencies "+dropDownSelector.getText());

    }

    @Test
    public void dynamicDropDown() {
        WebElement dynamicDropDown = driver.findElement(By.xpath("//input[@value='Departure City']"));
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        dynamicDropDown.click();
        WebElement gwalior = driver.findElement(By.xpath("//a[@value='GWL']"));
        gwalior.click();
        //Find element by using child xpath
        WebElement blr = driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='BLR']"));
        //Find element by using index of the element occurance
        //WebElement blr = driver.findElement(By.xpath("(//a[@value='BLR'])[2]"));
        blr.click();

    }

    @Test(enabled = false)
    public void spiceJetDropDown() {
        driver.get("https://www.spicejet.com/default.aspx");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.id("divpaxinfo")).click();

    }


}
