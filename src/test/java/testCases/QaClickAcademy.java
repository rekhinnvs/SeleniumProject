package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.concurrent.TimeUnit;

public class QaClickAcademy {

    WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = Base.initializeDriver();
        driver.get("http://www.qaclickacademy.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void courses() {
        //finding the object by its siblings.
        driver.findElement(By.xpath("//li[@class='active']/following-sibling::li[1]"));
        //Finding the object by its parent.
        driver.findElement(By.xpath("//li[@class='active']/parent::ul"));
        //Finding the object by its text.
        driver.findElement(By.xpath("//*[text()='Interview Guide']")).click();
    }
}
