package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage{

    private WebDriver driver;
    private WebElement queryBox;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.queryBox = driver.findElement(By.cssSelector("#searchString"));
        PageFactory.initElements(driver, this);
    }

    public void searchInputQuery(String searchQuery){
        this.queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys(searchQuery);
    }

    public ResultsPage searchReturnKey(){
        queryBox.sendKeys(Keys.RETURN);
        return new ResultsPage(driver);
    }

    public ResultsPage searchClickButton(){
        driver.findElement(By.cssSelector("#generalSearch > div.field.field-right > button")).click();
        return new ResultsPage(driver);
    }

    public ResultsPage searchFormSubmit(){
        driver.findElement(By.cssSelector("#generalSearch")).submit();
        return new ResultsPage(driver);
    }


}
