package testCases;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import javax.print.DocFlavor;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChildWindows extends Base {
    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void launchChildWindow() {
        driver.findElement(By.cssSelector("a[href='https://www.w3schools.com']")).click();
        Set<String> windowIDs = driver.getWindowHandles();
        Iterator<String> iterator = windowIDs.iterator();
        String parentID = iterator.next();
        String childID = iterator.next();
        driver.switchTo().window(childID);
        System.out.println(driver.getTitle());
        driver.findElement(By.cssSelector("a[href='/html/default.asp']")).click();
        driver.switchTo().window(parentID);
    }

}
