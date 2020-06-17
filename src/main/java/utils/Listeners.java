package utils;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends Base implements ITestListener {
    Logger logger;

    @Override
    public void onTestStart(ITestResult result) {
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
        logger.info("Test case " + result.getName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test case " + result.getName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
}
