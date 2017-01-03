package test.simple;


import com.project.selenium.MainPage;
import com.project.selenium.SearchPage;
import com.project.selenium.Setup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchBoxTests {

    @BeforeClass
    public static void setUp() throws Exception {
        Setup.getDriver();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Setup.quit();
    }

    @Test
    public void testSuggestionBox(){
        MainPage mainPage=new MainPage();
        String searchedProduct = "dress";

        mainPage.searchProduct(searchedProduct);
        mainPage.verifySearchSuggestion(searchedProduct);
        mainPage.openFirstElementFromSuggestionBox();
    }

    @Test
    public void testSearchWarnings(){
        MainPage mainPage=new MainPage();
        String notExistingProduct = "aaaaa";
        String existingProduct = "PRINTED SUMMER DRESS";

        mainPage.clickSearchButton()
                .verifyWarningMessage("Please enter a search keyword")
                .searchProductAndConfirm(notExistingProduct)
                .verifyWarningMessage("No results were found for your search \"" + notExistingProduct + "\"")
                .searchProductAndConfirm(existingProduct)
                .verifyWarningIsNotDisplayed()
                .verifyProductIsFound(existingProduct);
    }
}
