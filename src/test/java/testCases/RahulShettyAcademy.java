package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RahulShettyAcademy extends Base {

    static WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void OpenInNewTab() throws InterruptedException {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        //Get the first footer column object
        WebElement footerColumn = driver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        //Get all the links in the first footer column
        List<WebElement> linksInFooterColumn = footerColumn.findElements(By.tagName("a"));
        System.out.println(footerColumn.findElements(By.tagName("a")).size());
        //To open a link in new tab in Mac use Command+Enter. In Windows Control+Enter
        String controlClick = Keys.chord(Keys.COMMAND, Keys.ENTER);

        for(int i=1; i<linksInFooterColumn.size(); i++) {
            linksInFooterColumn.get(i).sendKeys(controlClick);
        }
        Thread.sleep(5000);
        Set<String> numberOfWindows = driver.getWindowHandles();
        Iterator<String> iterator = numberOfWindows.iterator();
        while(iterator.hasNext()) {
            driver.switchTo().window(iterator.next());
            System.out.println(driver.getTitle());
        }
        driver.switchTo().defaultContent();


    }
}
