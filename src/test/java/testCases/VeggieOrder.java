package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Base;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VeggieOrder extends Base{

    static WebDriver driver;

    @BeforeClass
    public void  initDriver() throws InterruptedException {
        driver = Base.initializeDriver();
        driver.get("https://www.rahulshettyacademy.com/seleniumPractise/#/");
        implicitWaitFor(5);
    }

    @Test(dataProvider="veggieNames", priority = 1)
    public void orderVeggies(String vegitableName) throws InterruptedException {

        List<WebElement> veggieNames = driver.findElements(By.cssSelector("h4.product-name"));

        for(int i=0; i<veggieNames.size();i++) {
            System.out.println(veggieNames.get(i).getText());
            if(veggieNames.get(i).getText().contains(vegitableName)) {
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                break;
            }
        }
        Thread.sleep(1000);

    }

    @Test(priority = 2)
    public void checkOut() {
        driver.findElement(By.cssSelector("a.cart-icon")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
    }

    @DataProvider
    public Object[] veggieNames() {
        Object[] vegitables = {"Cucumber","Carrot", "Brocolli"};

        return vegitables;
    }
}
