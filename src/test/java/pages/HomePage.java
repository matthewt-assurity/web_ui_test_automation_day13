package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void search(String searchQuery){
        WebElement queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys(searchQuery);
    }
}
