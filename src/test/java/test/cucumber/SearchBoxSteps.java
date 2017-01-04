package test.cucumber;

import com.project.selenium.MainPage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import static com.project.selenium.Setup.getDriver;

public class SearchBoxSteps {

    public static WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        driver = getDriver();
    }

    @After
    public void cleanUp(){
        driver.close();
    }

    @Given("^User is on Main Page$")
    public void openMainPage() {
        mainPage = new MainPage();
    }

    @When("^User enter product \"([^\"]*)\" into SearchBox$")
    public void enterProductNameToSearchBox(String searchedProduct) {
        mainPage.searchProduct(searchedProduct);
    }

    @Then("^Appears suggestion under Searchbox with \"([^\"]*)\"$")
    public void appearsSuggestionBox(String searchedProduct) {
        mainPage.verifySearchSuggestion(searchedProduct);
    }
}
