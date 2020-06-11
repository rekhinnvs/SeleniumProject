package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Base {

    static WebDriver driver;

    public static WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver initializeCustomDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        return driver;
    }

    public static WebDriver initializeRemoteDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        /*GridLauncherV3.main(new String[] { "-role", "node", "-hub",
                 "http://localhost:4444", "-browser",
               "browserName=chrome", "-port", "5555" });*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        //capabilities.setCapability(CapabilityType.BROWSER_VERSION,"83.0.4103.61");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
        return driver;
    }

    public static void implicitWaitFor(int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public static XSSFSheet getDataFromExcel() throws IOException {
        FileInputStream fis = new FileInputStream("./resources/testData.xlsx");
        //Create a workbook object
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = null;
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            //get the sheet
            if (workbook.getSheetName(i).equalsIgnoreCase("users"))
                sheet = workbook.getSheetAt(i);

        }
        return sheet;
    }

}
