package testCases;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class QaClickAcademy {

    WebDriver driver;

    @BeforeClass
    public void initDriver() {
        driver = Base.initializeDriver();
        //driver.get("http://www.qaclickacademy.com/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(groups = "Sanity")
    public void courses() {
        //finding the object by its siblings.
        driver.findElement(By.xpath("//li[@class='active']/following-sibling::li[1]"));
        //Finding the object by its parent.
        driver.findElement(By.xpath("//li[@class='active']/parent::ul"));
        //Finding the object by its text.
        driver.findElement(By.xpath("//*[text()='Interview Guide']")).click();
    }

    @Test(groups = "Smoke")
    public void radioButton() {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.xpath("//*[@value='radio2']")).click();
        WebElement autoComplete = driver.findElement(By.id("autocomplete"));
        autoComplete.sendKeys("new");
        autoComplete.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);

        Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
        select.selectByValue("option2");


    }

    @Test
    public void getNumberOfCheckBox() {
        //System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
        List<WebElement> checkBoxes;
        checkBoxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        System.out.println("Number of checkboxes "+checkBoxes.size());
        for(int i=0;i<checkBoxes.size();i++) {
            System.out.println("In loop "+i);
        }
    }

    @Test
    public void alertDialoq() throws InterruptedException {
        driver.findElement(By.cssSelector("[id='name']")).sendKeys("Rekhin");
        driver.findElement(By.id("alertbtn")).click();
        Thread.sleep(2000);
        String enteredText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertTrue(enteredText.contains("Rekhin"));
        System.out.println("Entered text "+enteredText);
    }
}
