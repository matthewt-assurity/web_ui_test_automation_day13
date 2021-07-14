package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private WebElement queryBox;

    public HomePage(WebDriver driver){
        super(driver);
        this.queryBox = driver.findElement(By.cssSelector("#searchString"));
    }

    public void searchInputQuery(String searchQuery){
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
