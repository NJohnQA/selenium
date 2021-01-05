package task4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class taskFour
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
    public void addAUser()
    {
        String email = "fafofo6682@chomagor.com";

        driver.get("http://demo.guru99.com/test/newtours/");
        driver.findElement(By.linkText("REGISTER")).click();
        driver.findElement(By.name("firstName")).sendKeys("Joe");
        driver.findElement(By.name("lastName")).sendKeys("Blogs");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("userName")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("confirmPassword")).sendKeys("password");
        driver.findElement(By.cssSelector("form tr:nth-child(5) > td:nth-child(2)")).click();
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("Home")).click();
        driver.findElement(By.linkText("SIGN-ON")).click();
        driver.findElement(By.name("userName")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("submit")).click();
        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Login Successfully");
    }

}
