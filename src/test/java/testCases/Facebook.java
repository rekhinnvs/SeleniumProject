package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import utils.Base;

import java.util.concurrent.TimeUnit;


public class Facebook {
    static WebDriver driver;

    @BeforeTest
    public void initDriver() {
        System.out.println("Initializing driver");
        driver = Base.initializeDriver();
    }
    @Test
    public void launchChrome() {

        driver.get("https://facebook.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String title = driver.getTitle();
        System.out.println(title);
        System.out.println(driver.getCurrentUrl());
    }
    @Test(enabled = false)
    public void loginToFacebook() {
        /**  xpath syntax
            //tagname[@attribute='value']
         **/
        driver.findElement(By.id("email")).sendKeys("dracoz");
        driver.findElement(By.id("pass")).sendKeys("kukkoos");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
    }

    @Test(enabled = false)
    public void loginByCss() {
        /** CSS syntax are
            tagname[Attribute=value]
            tagname#id
            tagname.classname
            .className#id
            #id
         **/
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("hellow");
        driver.findElement(By.cssSelector("input[id='pass']")).sendKeys("hereIam");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @Test
    public void loginByRegularExpression() {
        /**
         * Regular expression for xpath and css selector are
         * xpath //
         * tagname[contains(@attribute,'value')]
         * CSS tagname[attribute*=value]
         */

        driver.findElement(By.xpath("//input[contains(@name,'emai')]")).sendKeys("userName");
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("Password");

    }
}
