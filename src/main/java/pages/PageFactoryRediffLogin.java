package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryRediffLogin {

    WebDriver driver;
    @FindBy(css = ".signin")
    WebElement loginButton;
    @FindBy(xpath = "//input[contains(@id,'login')]")
    WebElement userName;
    @FindBy(xpath = "//input[contains(@name,'pass')]")
    WebElement password;
    @FindBy(css = "input[value*='Sign']")
    WebElement signIn;

    // Initialize the driver object.
    public PageFactoryRediffLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSignIn() {
        return signIn;
    }
}
