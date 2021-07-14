package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage extends AbstractPage{

    public ResultsPage(WebDriver driver) {
        super(driver);
        waitForFooter();
    }

    public String getTotalCount() {
        return driver.findElement(By.cssSelector("#totalCount")).getText();
    }

    public void waitForFooter(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#top-of-page > div.site-footer")));
    }
}
