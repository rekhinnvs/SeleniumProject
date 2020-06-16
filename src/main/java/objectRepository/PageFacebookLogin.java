package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageFacebookLogin {
    WebDriver driver;
    By userName = By.id("email");
    By password = By.id("pass");
    By loginButton = By.xpath("//input[@value='Log In']");


    public PageFacebookLogin(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement userName() {
        return driver.findElement(userName);
    }

    public WebElement password() {
        return driver.findElement(password);
    }

    public WebElement login() {
        return driver.findElement(loginButton);
    }
}
