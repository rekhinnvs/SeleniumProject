package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends Base implements ITestListener {
    Logger logger;
    Base base;
    WebDriver driver = null;
    ExtentReports extent;
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest;

    @Override
    public void onStart(ITestContext context) {
        //Create extend report object and initialize it.
        base = new Base();
        extent = base.htmlReporter();
        // To enable thread safe, so all the parallel run result will save in the same extend report file.
        extentTest = new ThreadLocal<>();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
        //To make it thread safe
        extentTest.set(test);
        logger = getLogger(result.getTestClass().getName());
        logger.debug("Test started");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.debug("Test case " + result.getName() + " passed");
        extentTest.get().pass("Test case passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        //Get the current driver object from the test case.
        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute("WebDriver");
        System.out.println(driver.getTitle());
        extentTest.get().fail(result.getThrowable());
        logger.info("Test case " + result.getName() + " failed");
        try {
            File screenshotPath = base.takeScreenShots(driver, result.getName());
            System.out.println("screenshot path from try block " + screenshotPath.getAbsolutePath());
            extentTest.get().addScreenCaptureFromPath(screenshotPath.getAbsolutePath(), result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test case " + result.getName() + " skipped");
        extentTest.get().skip("Test case skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
