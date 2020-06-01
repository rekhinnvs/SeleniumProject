package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.concurrent.TimeUnit;

public class FramesInAPage extends Base {

    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void switchToFrame() {
        WebElement newFrame = driver.findElement(By.cssSelector("iframe.demo-frame"));
        driver.switchTo().frame(newFrame);
    }

    @Test(priority = 2)
    public void dragAndDropInAFrame() {
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        //Drag the source element to target element
        actions.dragAndDrop(source, target).build().perform();

        //Switch back to main window from the frame.
        driver.switchTo().defaultContent();
    }
}
