package interviewPrep;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.io.File;
import java.net.URL;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Class_1 extends Base {
    WebDriver driver;
    Properties properties;

    @BeforeClass
    public void setup() throws IOException {
        driver = initializeDriver();
        implicitWaitFor(3);
        properties = new Properties();
        FileInputStream fis = new FileInputStream("./src/data.properties");
        properties.load(fis);
    }

    @Test(enabled = false)
    public void findBrokenLinks() {
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        String baseUrl = properties.getProperty("rahulShetty");
        driver.get(baseUrl);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links " + links.size());
        for (WebElement link : links) {
            url = link.getAttribute("href");
            //System.out.println(url);
            if (url == null || url.isEmpty()) {
                //System.out.println(link.getAttribute());
                System.out.println("Url is either null or empty");
                continue;
            }
            if (!url.startsWith(baseUrl)) {
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if (respCode >= 400) {
                    System.out.println(url + " is a broken link");
                } else {
                    System.out.println(url + " is a valid link");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @Test(enabled = false)
    public void scrollDynamicPage() throws InterruptedException {
        driver.get(properties.getProperty("rahulShetty"));
        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

        while (true) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);

            long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }

    }

    @Test(enabled = false)
    public void findLength() {
        String string = "Rekhin hello";
        System.out.println(string.lastIndexOf(""));
    }

    @Test(enabled = false)
    public void flipKartSort() throws InterruptedException {
        /*Using Selenium, load the flipkart.com desktop home page.
        Search for the product "Samsung Galaxy S10" on that page.
                On the search results click on "Mobiles" in categories.
        Apply the following filters (in filters section on the left hand side):
        Brand: Samsung, Select Flipkart assured,Sort the entries with Price -> High to Low.
        Read the set of results that show up on page 1.
        Create a list with the following parameters, and print this on the console.
        Product Name, Display Price, Link to Product Details Page*/

        driver.get(properties.getProperty("flipkart"));
        WebElement closeLoginBanner = driver.findElement(By.className("_3Njdz7"));
        if (closeLoginBanner.isDisplayed()) {
            System.out.println("Banner is displayed");
            driver.findElement(By.xpath("//div[@class='_3Njdz7']/button")).click();
        }

        driver.findElement(By.xpath("//input[@class='LM6RPg']")).sendKeys("Samsung");
        driver.findElement(By.className("vh79eN")).click();
        driver.findElement(By.xpath("//div[contains(text(),'High to Low')]")).click();
        List<WebElement> searchResults = driver.findElements(By.cssSelector("._3O0U0u"));
        System.out.println("Number of result " + searchResults.size());
        Thread.sleep(2000);
        for (WebElement element : searchResults) {
            System.out.println("_______________________________________");
            WebElement productName = element.findElement(By.cssSelector("._3wU53n"));
            System.out.println("Product name : " + productName.getText());

            WebElement displayPrice = element.findElement(By.cssSelector("._1vC4OE._2rQ-NK"));
            System.out.println("Display price : " + displayPrice.getText());

            WebElement links = element.findElement(By.cssSelector("._31qSD5"));
            System.out.println("Link : " + links.getAttribute("href"));
            System.out.println("_________________________________________");
        }
    }

    @Test(enabled = false)
    public void sportsAda() {
        /*visit: https://www.sportsadda.com/
        Click on got it/Cookies button(visible at the bottom of the page that accepts cookies)
        One Ad pops up which needs to be closed by automation
        I tried with Selenium but have no clue how to close it
        Move ahead without login.
        RHS Scores widget: Click on very first match scorecard
        Click on Match info tab*/
        driver.get(properties.getProperty("sportsadda"));
        driver.manage().window().maximize();
        WebElement cookies = driver.findElement(By.id("cookiebtn"));
        if (cookies.isDisplayed()) cookies.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement adBanner = driver.findElement(By.cssSelector(".close-section"));
        wait.until(ExpectedConditions.elementToBeClickable(adBanner));
        adBanner.click();

        WebElement scoreCard = driver.findElement(By.xpath("//div[contains(@class,'swiper-slide-active')]"));
        WebElement container = driver.findElement(By.xpath("//div[@class='swiper-wrapper']//child::div[1]"));
        //div[@class='form-group']//child::input[1]


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", container);

        Actions actions = new Actions(driver);
        actions.moveToElement(container).perform();
    }

    @Test
    public void getAllLinks() throws IOException {
        driver.get("https://www.conestogac.on.ca/");
        List<WebElement> links = driver.findElements(By.tagName("a"));

        Iterator<WebElement> it = links.iterator();
        String url = "";
        /*while(it.hasNext()) {

            url = it.next().getAttribute("href");

            System.out.println(url);

            if (url == null || url.isEmpty()) {
                System.out.println("URL is empty ");
                continue;
            }
        }*/

        for (WebElement element : links) {
            url = element.getAttribute("href");
            if (url == null || url.isEmpty()) {
                //System.out.println(element.getAttribute(""));
                System.out.println(element.getAttribute("innerHTML"));
                System.out.println("URL is empty " + element.getText());
                continue;
            }
        }

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
