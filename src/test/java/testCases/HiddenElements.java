package testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.concurrent.TimeUnit;

public class HiddenElements extends Base {

    static WebDriver driver;

    @BeforeClass
    public void initDriver(){
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void ksrtcFrom() {
        driver.get("https://ksrtc.in/oprs-web/guest/home.do");
        WebElement from = driver.findElement(By.id("fromPlaceName"));
        from.sendKeys("Beng", Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        //Since the text box values are hidden, use JavaScriptExecutor to get the value from the text box.
        String script = "return document.getElementById('fromPlaceName').value;";
        //Execute the script and get the value.
        String enteredValue = (String) javascriptExecutor.executeScript(script);
        System.out.println("Text from suggestion "+enteredValue);
    }
}
