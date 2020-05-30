package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class Base {

    static WebDriver driver;
    public static WebDriver initializeDriver() {
        //Since the latest chrome ver 83 is not yet available, use the below code instead
        //WebDriverManager.chromedriver().useMirror().setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        return driver;
    }

    public static void implicitWaitFor(int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

}
