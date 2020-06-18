package utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {
    Logger logger;
    Base base;
    WebDriver driver = null;

    @Override
    public void onTestStart(ITestResult result) {
        base = new Base();
        logger = getLogger(result.getTestClass().getName());
        System.out.println("Class name " + result.getTestClass().getName());
        logger.debug("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.debug("Test case " + result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        //Get the current driver object from the test case.
        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute("WebDriver");
        System.out.println(driver.getTitle());
        logger.info("Test case " + result.getName() + " failed");
        try {
            System.out.println("From try block " + driver.getTitle());
            System.out.println("From try block " + result.getName());
            base.takeScreenShots(driver, result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test case " + result.getName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
}
