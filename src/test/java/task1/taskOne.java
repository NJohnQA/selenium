package task1;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class taskOne
{
    private WebDriver driver;

    @BeforeClass
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
    public void addAUser() throws InterruptedException {
        driver.get("http://thedemosite.co.uk/");

        // Create a User
        driver.findElement(By.linkText("3. Add a User")).click();
        driver.findElement(By.name("username")).sendKeys("guest");
        driver.findElement(By.name("password")).sendKeys("guest");
        driver.findElement(By.name("FormsButton2")).click();

        // Login new User
        driver.findElement(By.linkText("4. Login")).click();
        driver.findElement(By.name("username")).sendKeys("guest");
        driver.findElement(By.name("password")).sendKeys("guest");
        driver.findElement(By.name("FormsButton2")).click();

        // Assert Test
        assertEquals(driver.findElement(By.cssSelector("center > b")).getText(), "**Successful Login**");
    }


}
