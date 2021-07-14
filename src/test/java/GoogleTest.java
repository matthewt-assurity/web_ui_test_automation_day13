import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTest {

    private WebDriver driver;

    @BeforeAll
    private static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    private void setupBrowser() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @AfterEach
    private void teardownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

//    @Test
//    public void checkTitle() {
//        assertEquals("Google", driver.getTitle());
//    }

    @Test
    public void testCheese() throws Exception {
        WebElement queryBox = driver.findElement(By.name("q"));
        queryBox.sendKeys("tea");
        queryBox.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
    }

    @Test
    public void testTradeMeReturnKey() throws Exception{
        driver.get("https://www.tmsandbox.co.nz/");
        WebElement queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys("camera");
        queryBox.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
    }

    @Test
    public void testTradeMeSubmitForm() throws Exception{
        driver.get("https://www.tmsandbox.co.nz/");
        WebElement queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys("camera");
        WebElement submitButton = driver.findElement(By.cssSelector("#generalSearch > div.field.field-right > button"));
        submitButton.click();
        Thread.sleep(2000);
    }


}