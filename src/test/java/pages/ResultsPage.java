package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage{

    WebDriver driver;
    @FindBy(css="#totalCount")
    private WebElement totalCount;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        waitForFooter();
        PageFactory.initElements(driver, this);
    }

    public String getTotalCount() {
        return totalCount.getText();
    }

    public void waitForFooter(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#top-of-page > div.site-footer")));
    }
}
