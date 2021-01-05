package task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class taskTwo
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
    public void findAnItem()
    {
        driver.get("http://automationpractice.com/index.php");

        // Search for something
        driver.findElement(By.id("search_query_top")).sendKeys("dress");
        driver.findElement(By.id("search_query_top")).sendKeys(Keys.ENTER);

        // Wait for the new page to load
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/ul/li[1]")));

        // Check for a link on the page with specific text
        assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Printed Summer Dress')])[3]")).getText(), "Printed Summer Dress");
    }

    @Test
    public void checkoutItem()
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("http://automationpractice.com/index.php");
        String userEmail = "fimawed908@chomagor.com";

        // Search for something
        driver.findElement(By.id("search_query_top")).sendKeys("dress");
        driver.findElement(By.id("search_query_top")).sendKeys(Keys.ENTER);

        // Wait for the new page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ajax_block_product:nth-child(1)")));

        // Hover the first item & Click Add to Cart
        WebElement element = driver.findElement(By.cssSelector(".ajax_block_product:nth-child(1)"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
        driver.findElement(By.cssSelector(".ajax_block_product:nth-child(1) .button:nth-child(1) > span")).click();

        // Wait for Modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#layer_cart")));
        driver.findElement(By.cssSelector(".button-medium:nth-child(2) > span")).click();

        // Wait for Continue to Checkout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".standard-checkout > span")));
        driver.findElement(By.cssSelector(".standard-checkout > span")).click();

        // Wait for Create Account
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
        driver.findElement(By.id("email_create")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("#SubmitCreate > span")).click();

        // Wait for Account Details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));

        // Populate User Details
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Joe");
        driver.findElement(By.id("customer_lastname")).sendKeys("Blogs");
        driver.findElement(By.id("passwd")).sendKeys("password");
        Select days = new Select(driver.findElement(By.id("days")));
        days.selectByValue("20");
        Select months = new Select(driver.findElement(By.id("months")));
        months.selectByIndex(6);
        Select years = new Select(driver.findElement(By.id("years")));
        years.selectByValue("2000");

        // Populate User Address
        driver.findElement(By.id("address1")).sendKeys("2503 Dearborn St");
        driver.findElement(By.id("city")).sendKeys("Salt Lake City");
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByVisibleText("Utah");
        driver.findElement(By.id("postcode")).sendKeys("84106");
        driver.findElement(By.id("phone_mobile")).sendKeys("(801) 484-3444");
        driver.findElement(By.id("alias")).sendKeys("Work");
        driver.findElement(By.cssSelector("#submitAccount > span")).click();

        // Wait for account created
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button:nth-child(4) > span")));
        driver.findElement(By.cssSelector(".button:nth-child(4) > span")).click();

        // Wait for Terms Checkbox
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("cgv")));
        driver.findElement(By.name("cgv")).click();
        driver.findElement(By.cssSelector(".standard-checkout > span")).click();

        // Wait for payment button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pay by bank wire (order processing will be longer)")));
        driver.findElement(By.linkText("Pay by bank wire (order processing will be longer)")).click();

        // Wait for cart button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cart_navigation > button")));
        driver.findElement(By.cssSelector("#cart_navigation > button")).click();

        // Wait for order completed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cheque-indent > .dark")));
        assertEquals(driver.findElement(By.cssSelector(".cheque-indent > .dark")).getText(), "Your order on My Store is complete.");
    }
}
