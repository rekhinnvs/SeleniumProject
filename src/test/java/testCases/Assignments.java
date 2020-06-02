package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Assignments extends Base{

    static WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(description = "Assignment 2",enabled = false)
    public void searchForFlights() throws  InterruptedException{
        driver.get("https://www.cleartrip.com/");
        driver.findElement(By.id("DepartDate")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active ")).click();
        Select selectAdults = new Select(driver.findElement(By.id("Adults")));
        selectAdults.selectByValue("3");

        Select selectChildren = new Select(driver.findElement(By.id("Childrens")));
        selectChildren.selectByValue("2");
        driver.findElement(By.id("MoreOptionsLink")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("AirlineAutocomplete")).sendKeys("Indigo");
        driver.findElement(By.id("SearchBtn")).click();
        Thread.sleep(2000);
        String errorMessage = driver.findElement(By.id("homeErrorMessage")).getText();
        System.out.println(errorMessage);

    }

    @Test(description = "Assignment 3",enabled = false)
    public void explicityWait() {
        driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");
        driver.findElement(By.xpath("//div[@id='content']/a[2]")).click();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));
        System.out.println(driver.findElement(By.id("results")));
    }

    @Test(description = "Fluent wait", enabled = false)
    public void fluentWait() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("[id='start'] button")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(4))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()) {
                    System.out.println(driver.findElement(By.cssSelector("[id='finish'] h4")).getText());
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                }
                else
                    return null;
            }
        });

    }

    @Test(description = "Handling multiple windows", enabled = false)
    public void multipleWindows() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.cssSelector("a[href='/windows']")).click();
        driver.findElement(By.cssSelector("a[href='/windows/new']")).click();
        Set<String> windowIDs = driver.getWindowHandles();
        Iterator<String> iterator = windowIDs.iterator();
        String parentID = iterator.next();
        String childID = iterator.next();

        //Switch to the child window.
        driver.switchTo().window(childID);
        System.out.println(driver.findElement(By.cssSelector("div.example h3")).getText());

        //switch back to parent window
        driver.switchTo().window(parentID);
        System.out.println(driver.findElement(By.cssSelector("div.example h3")).getText());
    }

    @Test(description = "Frames", enabled = false)
    public void nestedFrames() {
        driver.get("https://the-internet.herokuapp.com/");
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/nested_frames']")));
        driver.findElement(By.cssSelector("a[href='/nested_frames']")).click();

        WebElement topFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(topFrame);
        System.out.println(driver.findElements(By.tagName("frame")).size());

        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        driver.switchTo().frame(middleFrame);
        System.out.println(driver.findElement(By.id("content")).getText());
        driver.switchTo().defaultContent();
    }

    @Test(description = "Find the number of links in a page",enabled = false)
    public void findLinks() {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        System.out.println("Number of links present " + driver.findElements(By.tagName("a")).size());

        WebElement footerSection = driver.findElement(By.id("gf-BIG"));
        //Number of links present in the footer section.
        System.out.println("Number of links present in the footer section "+ footerSection.findElements(By.tagName("a")).size());
    }

    @Test
    public void verifyAlert() {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        WebElement checkBoxOption2 = driver.findElement(By.id("checkBoxOption2"));
        checkBoxOption2.click();
        String option = checkBoxOption2.findElement(By.xpath("parent::label")).getText();
        //String option = checkBoxOption2.getAttribute("value");
        System.out.println(option);
        Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
        select.selectByVisibleText(option);
        driver.findElement(By.id("name")).sendKeys(option);
        driver.findElement(By.id("alertbtn")).click();
        String enteredText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertTrue(enteredText.contains(option));
        System.out.println("Entered text "+enteredText);

    }
}
