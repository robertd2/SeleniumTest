package test.cucumber;

import com.project.selenium.MainPage;
import com.project.selenium.SearchPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchBoxSteps {

    private MainPage mainPage;
    private SearchPage searchPage;

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

    @And("^Open first element from suggestion box$")
    public void openFirstElementFromSuggestionBox() {
        searchPage = mainPage.openFirstElementFromSuggestionBox();
    }

    @Then("^Opens page with \"([^\"]*)\"$")
    public void opensPageWithProduct(String productName){
        searchPage.verifyOpenedProductPage(productName);
    }
}
