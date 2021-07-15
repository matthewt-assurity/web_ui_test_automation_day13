package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.ResultsPage;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeMeSteps {

    private HomePage homePage;
    private ChromeDriver chromeDriver;
    private ResultsPage resultsPage;

    @Given("I am conducting a TradeMe search")
    public void i_am_conducting_a_trade_me_search() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        if(("true").equals(System.getenv("HEADLESS_CHROME"))){
            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        }
        chromeDriver = new ChromeDriver(options);
//        chromeDriver.get("https://www.tmsandbox.co.nz/");
        homePage = new HomePage(chromeDriver);
    }

    @When("I search for {string}")
    public void i_search_for(String string) {
        homePage.searchInputQuery(string);
        resultsPage = homePage.searchReturnKey();
    }

    @Then("I see {string} results")
    public void i_see_results(String num) {
        assertEquals(resultsPage.getTotalCount(), num);
    }

    @After
    public void cleanUp(){
        chromeDriver.quit();
    }
}
