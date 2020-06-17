package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

public class ExtendReporter implements IReporter {
    ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        if (true) {
            htmlReporter = new ExtentHtmlReporter("./output/htmlreport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        } else {

            ExtentLoggerReporter logger = new ExtentLoggerReporter("./output/");
            extent = new ExtentReports();
            extent.attachReporter(logger);
        }


        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }
        extent.flush();
    }

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
                String message = result.getMethod().getDescription();
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
                test.log(status, message);

            }
        }
    }
}
