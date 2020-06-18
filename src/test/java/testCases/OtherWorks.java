package testCases;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class OtherWorks extends Base {
    WebDriver driver;
    static Logger logger;
    Base base;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = initializeDriver();
        implicitWaitFor(3);
        context.setAttribute("WebDriver", driver);

    }

    @AfterClass
    public void tearDown() {
        //base.flushHtmlReport();
        //driver.quit();
    }

    @Test(description = "Change the currency and get the value.", enabled = true)
    public void spiceJetGetCurrency() {
        driver.get("https://www.spicejet.com/");
        Select currency = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        currency.selectByValue("USD");
        System.out.println(currency.getFirstSelectedOption().getText());
    }

    @Test(description = "select the passengers.", enabled = true)
    public void selectPassengers() {
        driver.get("https://www.spicejet.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passengers = driver.findElement(By.id("divpaxinfo"));
        wait.until(ExpectedConditions.textToBePresentInElement(passengers, "1 Adult"));
        //wait.until(ExpectedConditions.elementToBeClickable(passengers));
        passengers.click();
        //Select the number of adult passengers.
        Select selectAdult = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));
        selectAdult.selectByValue("4");
        //Select the number of child
        Select selectChild = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Child")));
        selectChild.selectByValue("3");
    }

    @Test(enabled = false)
    public void checkSSLCertificate() {
        driver = initializeCustomDriver();
        driver.get("https://tools.borqsindia.com/");
    }

    @Test(enabled = false)
    public void bigBasketNewWindow() throws InterruptedException {
        driver.get("https://www.bigbasket.com/");
        driver.manage().window().maximize();
        //Thread.sleep(30000);
        WebElement miniDriver = driver.findElement(By.xpath("(//*[@ng-controller='Footer as vm']/div/div[2])[1]"));
        System.out.println(miniDriver.findElements(By.tagName("a")).size());
        int size = miniDriver.findElements(By.tagName("a")).size();
        for (int i = 0; i < size; i++) {
            String Controlenter = Keys.chord(Keys.COMMAND, Keys.ENTER);
            miniDriver.findElements(By.tagName("a")).get(i).sendKeys(Controlenter);
        }
    }

    @Test(enabled = false)
    public void manageCookies() {
        //Maximize the window
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //Delete a cookie with a name.
        driver.manage().deleteCookieNamed("cookieName");
    }

    @Test(description = "Take screenshots", enabled = false)
    public void takeScreenShots() throws IOException {
        driver.get("https://www.bigbasket.com/");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File outPutDirectory = new File("./output");
        //Delete all the files in the directory before copying the new files.
        FileUtils.cleanDirectory(outPutDirectory);
        FileUtils.copyFile(file, new File("./output/screenShot.png"));
    }

    @Test(description = "Sort table columns", enabled = true)
    public void sortTableColumns() {
        //base.createTest("sort columns");
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        WebElement fruitHeader = driver.findElement(By.cssSelector("tr th:nth-child(2)"));
        List<WebElement> fruitsElement = driver.findElements(By.cssSelector("tbody tr td:nth-child(2)"));
        ArrayList<String> sortedFruits = new ArrayList<>();
        for (WebElement fruit : fruitsElement) {
            sortedFruits.add(fruit.getText());
        }
        Collections.sort(sortedFruits, Collections.reverseOrder());

        fruitHeader.click();

        ArrayList<String> descendingFruitsFromPage = new ArrayList<>();
        for (WebElement fruit : fruitsElement) {
            descendingFruitsFromPage.add(fruit.getText());
        }
        if (sortedFruits.equals(descendingFruitsFromPage))
            System.out.println("Fruits are descending ordered");


    }

    @Test(enabled = false)
    public void getFromExcel() throws IOException {
        XSSFSheet sheet = getDataFromExcel();
        //This will throw exception if there is only one sheet in the workbook.
        Iterator<Row> rowIterator = sheet.iterator();
        Row firstRow = rowIterator.next();
        Iterator<Cell> cell = firstRow.cellIterator();
        System.out.print(cell.next().toString());
    }

    @Test(enabled = false)
    public void redBusInputSearchField() throws InterruptedException {
        //base.createTest("redbus search.");
        driver.get("https://www.redbus.in/");
        driver.findElement(By.id("src")).sendKeys("BENG");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='src']")).sendKeys(Keys.DOWN);
        //Since the text box values are hidden, use JavaScriptExecutor to get the value from the text box.
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "return document.getElementById('src').value;";
        String src = (String) javascriptExecutor.executeScript(script);
        System.out.println(src);
    }

    @Test(enabled = true)
    public void msnEnterSearchFlight() {
        driver.get("https://flights.msn.com/en-gb/flight-search");
        //Click on Flight Search link in the header
        //driver.findElement(By.linkText("Flight Search")).click();
        //Give Delhi in From TextBox
        WebElement continueSite = driver.findElement(By.xpath("//button[contains(@title,'Continue to site')]"));
        if (continueSite.isDisplayed()) {
            continueSite.click();
        }
        WebElement sourceField = driver.findElement(By.xpath("//a[contains(@class,'populated')]"));
        //Thread.sleep(3000);
        System.out.println(sourceField.getText());
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOf(fromPlace));
        //wait.until(ExpectedConditions.elementToBeClickable(fromPlace));
        sourceField.click();
        //WebElement fromPlace = driver.findElement(By.cssSelector(".place-selector__cover.text-ellipsis.js-autocomplete-place-cover.populated"));
        //fromPlace.sendKeys("hello");
        WebElement FromPlace = driver.findElement(By.xpath("//input[contains(@placeholder,'Origin city')]"));
        //FromPlace.clear();
        FromPlace.sendKeys("Delhi");
    }

    @Test(enabled = true)
    public void spiceJet() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        //Thread.sleep(2000);
        //Check Box Concepts :
        System.out.println(driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
        System.out.println(driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
        System.out.println(driver.findElements(By.cssSelector("input[type*='checkbox']")).size());
    }

    @Test(enabled = false)
    public void checkLogger() {
        logger = getLogger(this.getClass().getName());
        logger.info("This is an info log");
        logger.debug("This is a debug log.");
        logger.warn("This a warning log");
        logger.error("This is an error log");
        logger.fatal("This is a fatal error");
    }

    @Test(enabled = false)
    public void openFooterLinksInNewTab() {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.findElements(By.tagName("a")).size());
        WebElement footerdriver = driver.findElement(By.id("gf-BIG"));
        System.out.println(footerdriver.findElements(By.tagName("a")).size());
        WebElement colomoundriver = footerdriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        System.out.println(colomoundriver.findElements(By.tagName("a")).size());
        //String clickonlinkTab = Keys.chord(Keys.COMMAND, Keys.ENTER);
        for (int i = 1; i < colomoundriver.findElements(By.tagName("a")).size(); i++) {
            String clickonlinkTab = Keys.chord(Keys.COMMAND, Keys.ENTER);
            System.out.println(colomoundriver.findElements(By.tagName("a")).get(i).getText());
            colomoundriver.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
        }

    }

    @Test(enabled = false)
    public void salesForceLoginFailureCheck() throws InterruptedException {
        driver.get("https://www.salesforce.com/ca/?ir=1");
        driver.findElement(By.linkText("Login")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("anything");
        driver.findElement(By.xpath("//input[@name='pw']")).sendKeys("helpme");
        driver.findElement(By.cssSelector("#Login")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.cssSelector("div#error")).getText());
    }

    @Test(enabled = true)
    public void spiceJetOriginCity() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        //WebElement originCity = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        WebElement originCity = driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(originCity));
        originCity.click();

        String clickOnNewTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
        opportunities.sendKeys(clickOnNewTab);
    }

    @Test(enabled = true)
    public void aFailCase() {
        driver.get("http://google.com");
        Assert.fail("This is a failed case");
    }

}
