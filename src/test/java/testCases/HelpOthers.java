package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

public class HelpOthers extends Base {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = initializeDriver();
    }

    @Test
    public void salesForceLoginError() {


    }
}
