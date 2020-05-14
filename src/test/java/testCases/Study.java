package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Base;

import java.util.concurrent.TimeUnit;


public class Study{
    static WebDriver driver;

    @Test
    public void launchChrome() {
        System.out.println("Started the execution");
        driver = Base.initializeDriver();
        driver.get("https://google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String title = driver.getTitle();
        System.out.println(title);
    }

}
