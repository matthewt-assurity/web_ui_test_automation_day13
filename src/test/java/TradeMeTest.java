import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeMeTest {

    private WebDriver driver;

    @BeforeAll
    private static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    private void setupBrowser() {
        driver = new ChromeDriver();
        driver.get("https://www.tmsandbox.co.nz/");
    }

    @AfterEach
    private void teardownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testTradeMeReturnKey() throws Exception{
        WebElement queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys("gold");
        queryBox.sendKeys(Keys.RETURN);
        waitForFooter();
        printInfo();
    }

    @Test
    public void testTradeMeSubmitForm() throws Exception{
        typeGoldInSearch();
        driver.findElement(By.cssSelector("#generalSearch")).submit();
        waitForFooter();
        printInfo();
    }

    @Test
    public void testTradeMeClickSearchButton() throws Exception{
        typeGoldInSearch();
        driver.findElement(By.cssSelector("#generalSearch > div.field.field-right > button")).click();
        waitForFooter();
        printInfo();
    }

    private void typeGoldInSearch() {
        WebElement queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys("gold");
    }

    private void getNumListings(){
        System.out.println("Number of listings: " + driver.findElement(By.cssSelector("#totalCount")).getText());
    }

    private void getTopItemPrice(){
        System.out.println("Current price of top item: " +
                driver.findElements(
                        By.cssSelector("#SuperGridGallery_BucketList_ClassifiedPrice_listingClassifiedPriceAmountPoa"))
                        .get(0)
                        .getText());
    }

    private void clickListView(){
        driver.findElement(By.cssSelector("#ListingViewBar_listViewTab_icon_a")).click();
    }

    private void getTop10ListingTitles(){
        System.out.println("Top 10 Listing Titles:");
        List<WebElement> listings = driver.findElements(By.cssSelector(".title"));
        for(int i = 0; i < 10; i ++) System.out.println((i+1) + ". " +listings.get(i).getText());
    }

    private void printInfo() throws InterruptedException {
        System.out.println("--------------------");
        getNumListings();
        getTopItemPrice();
        clickListView();
        waitForFooter();
        getTop10ListingTitles();
        get3LowestBuyNow();
        System.out.println("--------------------");
    }

    private void waitForFooter(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#top-of-page > div.site-footer")));
    }

    private void get3LowestBuyNow(){
        Select select = new Select(driver.findElement(By.id("listingTitleBarSelect")));
        select.selectByVisibleText("Lowest Buy Now");

        List<WebElement> items = driver.findElements(By.className("info"));

        System.out.println("The 3 lowest price buy now items:");

        for(int i = 0; i < 3; i++){
            //title
            String title = items.get(i).findElement(By.className("title")).getText();
            // price
            List<WebElement> prices = new ArrayList<>();
            prices.addAll(driver.findElements(By.className("listingBidPrice")));
            prices.addAll(driver.findElements(By.className("listing-classified-price-amount")));
            System.out.println((i+1)+". "+title+", "+prices.get(i).getText());
        }
    }
}