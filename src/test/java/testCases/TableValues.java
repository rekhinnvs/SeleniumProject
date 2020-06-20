package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Base;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TableValues extends Base {
    static WebDriver driver;

    @BeforeClass
    public void initDriver(){
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void findTotalScore() {
        driver.get("https://www.cricbuzz.com/live-cricket-scorecard/27007/bcbxi-vs-zim-2-day-practice-match-zimbabwe-tour-of-bangladesh-2020");
        //driver.get("https://www.cricbuzz.com/live-cricket-scorecard/11839/ind-vs-pak-1st-t20i-pakistan-in-india-2012-13");
        WebElement firstInningsTable = driver.findElement(By.cssSelector("div[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));

        //Find all the rows in first innings.
        List<WebElement> runRows = firstInningsTable.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms']"));
        int totalScore = 0;
        int totalScoreFromPage = 0;
        //System.out.println(runRows.size());
        for (int i = 0; i < runRows.size(); i++) {

            //System.out.println(runRows.get(i).findElement(By.cssSelector(":nth-child(3)")).getText());
            //If it reached Extras field, get the extra run, 2nd column
            if (runRows.get(i).findElement(By.cssSelector(":nth-child(1)")).getText().equals("Extras")) {
                totalScore += Integer.parseInt(runRows.get(i).findElement(By.cssSelector(":nth-child(2)")).getText());
                System.out.println("Reached extras value, continuing");
                continue;
            }
            //Find the total runs from the page.
            if (runRows.get(i).findElement(By.cssSelector(":nth-child(1)")).getText().equals("Total")) {
                totalScoreFromPage = Integer.parseInt(runRows.get(i).findElement(By.cssSelector(":nth-child(2)")).getText());
                System.out.println("Reached Total Runs, breaking!");
                break;
            }
            //Find the run scored by batsman, 3rd column
            totalScore += Integer.parseInt(runRows.get(i).findElement(By.cssSelector(":nth-child(3)")).getText());
        }
        System.out.println("Total score counted : "+totalScore);
        Assert.assertEquals(totalScore,totalScoreFromPage);
    }
}
