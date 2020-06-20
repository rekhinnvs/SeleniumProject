package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Base {

    static ExtentReports extentReports;
    WebDriver driver;

    public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        return driver;
    }

    public WebDriver initializeCustomDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    public WebDriver initializeRemoteDriver() throws MalformedURLException {
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

    public void implicitWaitFor(int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public static XSSFSheet getDataFromExcel() throws IOException {
        FileInputStream fis = new FileInputStream("./resources/testData.xlsx");
        //Create a workbook object
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = null;

        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            //Traverse through every sheet and get the desired sheet.
            if (workbook.getSheetName(i).equalsIgnoreCase("users"))
                sheet = workbook.getSheetAt(i);

        }
        return sheet;
    }

    public static Logger getLogger(String className) {
        return LogManager.getLogger(className);
    }

    public ExtentReports htmlReporter() {
        String path = "output/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Test results");
        reporter.config().setReportName("Rekhin");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        return extentReports;
    }

    public File takeScreenShots(WebDriver driver, String testName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File outPutDirectory = new File("output");
        //Delete all the files in the directory before copying the new files.
        //FileUtils.cleanDirectory(outPutDirectory);
        File screenShotFile = new File(outPutDirectory.toString() + "/" + testName + ".png");
        FileUtils.copyFile(file, screenShotFile);
        System.out.println("absolute path of the screenshot " + screenShotFile.getAbsolutePath());
        return screenShotFile;
    }

}
