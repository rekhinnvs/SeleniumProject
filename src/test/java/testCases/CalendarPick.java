package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarPick extends Base {
    static WebDriver driver;

    @BeforeClass
    public void initDriver(){
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void pickADate() {
        driver.get("https://www.cleartrip.com/");
        driver.findElement(By.id("DepartDate")).click();
        //find a common element in the date picker and store it in a list.
        List<WebElement> dates = driver.findElements(By.xpath("//td[@data-handler='selectDay']"));
        System.out.println(dates.size());
        System.out.println(dates.get(50).getAttribute("data-month"));
        for(int i=0; i<dates.size(); i++){
            if(dates.get(i).getAttribute("data-month").equals(Integer.toString(6))) {
                //get the text and click on the date which is matching to 18
                if(dates.get(i).getText().equals(Integer.toString(18)))
                    dates.get(i).click();

            }
        }
    }

}
