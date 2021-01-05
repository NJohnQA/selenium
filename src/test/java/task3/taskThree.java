package task3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class taskThree
{
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1746, 758));
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void getTopRiser()
    {
        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        driver.findElement(By.id("acceptCookie")).click();
        driver.findElement(By.cssSelector("#view-constituents > ul > li:nth-child(2) > a")).click();
        String first = driver.findElement(By.cssSelector("#view-constituents > div.table-overflow-wrapper > table > tbody > tr:nth-child(1) > td:nth-child(2) > a")).getText();

        System.out.println(first);
    }

    @Test
    public void getTopFaller()
    {
        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        driver.findElement(By.id("acceptCookie")).click();
        driver.findElement(By.cssSelector("#view-constituents > ul > li:nth-child(3) > a")).click();
        String first = driver.findElement(By.cssSelector("#view-constituents > div.table-overflow-wrapper > table > tbody > tr:nth-child(1) > td:nth-child(2) > a")).getText();

        System.out.println(first);
    }


}
