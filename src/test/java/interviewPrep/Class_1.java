package interviewPrep;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Test
    public void findLength() {
        String string = "Rekhin hello";
        System.out.println(string.lastIndexOf(""));
    }
}
