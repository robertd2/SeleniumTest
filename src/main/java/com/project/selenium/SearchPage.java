package com.project.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchPage extends BasePage{

    private String title = "Search - My Store";

    private static final By warningMessageBox = By.cssSelector("p.alert-warning");
    private static final By searchedProductNameBox = By.cssSelector("h1.product-listing>span.lighter");
    private static final By searchedProductList = By.cssSelector("ul.product_list >li");

    public SearchPage(){
        driver = Setup.getDriver();
        verifyTitle(title);
    }

    public SearchPage(String productName){
        driver = Setup.getDriver();
        verifyTitleContains(productName);
    }

    private void verifyTitleContains(String partOfTitle){
        String fullTitle = driver.getTitle();
        assertTrue("Title (" + fullTitle + ") doesn't contain text: " + partOfTitle,
                fullTitle.contains(partOfTitle));
    }

    public SearchPage verifyWarningMessage(String warningMessage){
        String currentWarningMessage = driver.findElement(warningMessageBox).getText();
        assertTrue("Current warning message (" + currentWarningMessage + ") doesn't contain string:" + warningMessage,
                currentWarningMessage.contains(warningMessage));
        return this;
    }

    public SearchPage verifyWarningIsNotDisplayed(){
        List<WebElement> warningMessageBoxLists = driver.findElements(warningMessageBox);
        assertTrue("Warning box is displayed but should not",
                warningMessageBoxLists.size()<1);
        return this;
    }

    public SearchPage verifyProductIsFound(String productName){
        String searchHeader = driver.findElement(searchedProductNameBox).getText().toLowerCase();
        assertTrue("Searched product (" + productName + ") is different than search header:" + searchHeader,
                searchHeader.contains(productName.toLowerCase()));
        List<WebElement> searchedProduct = driver.findElements(searchedProductList);
        assertTrue("List of product is empty",searchedProduct.size()>0);
        return this;
    }


}
