package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import utils.Base;

public class WebSiteActions {

    static WebDriver driver;
    static Actions actions;

    @BeforeClass
    public void initDriver() {
        driver = Base.initializeDriver();
        actions = new Actions(driver);
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void mouseHover() {
        //driver.get("https://www.amazon.com/");
        // Move to a specific element.
        actions.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
    }

    @Test
    public void EnterInCaps() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("ssd harddisk").doubleClick().build().perform();
        actions.contextClick().build().perform();
    }

}
