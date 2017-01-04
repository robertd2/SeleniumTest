package com.project.selenium.purchase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.project.selenium.MainPage;

public class PurchasePage extends MainPage {

	private static final By productList = By.cssSelector("ul.product_list div.product-container");
	private static final By addToCartButton = By.cssSelector("div.button-container > a.ajax_add_to_cart_button");
	private static final By continueShoppingButton = By.cssSelector("div.button-container span.continue span");
	private static final By proceedToCheckoutButton = By.cssSelector("div.button-container a.btn[title='Proceed to checkout']");
	private static final By casualDressesButton = By.cssSelector("div.block_content a[title*='dress for every day']");
	private static final By eveningDressesButton = By.cssSelector("div.block_content a[title*='different dresses']");
	private static final By summerDressesButton = By.cssSelector("div.block_content a[title*='dress for summer']");
	private static final By topsButton = By.cssSelector("div.block_content a[title*='blouses']");

	public PurchasePage() {
		super();
	}
	
	protected PurchasePage clickElement(By element) {
		WebElement webElement = getElementIfExists(element);
		webElement.click();
		return this;
	}
	
	//choosing a category of product to buy
	
	/**
	 * Presses a T-Shirt button to move to t-shirts list
	 * @return the same page with t-shirts list content
	 */
	public PurchasePage clickTShirtButton() {
		return clickElement(tshirtMenuTab);
	}
	
	/**
	 * Presses a Dresses button to move to all dresses list
	 * @return the same page with all dresses listed
	 */
	public PurchasePage clickDressesButton() {
		return clickElement(dressMenuTab);
	}
	
	/**
	 * Presses a "Casual Dresses" button to move to casual dresses list
	 * @return the same page with casual dresses list content
	 */
	private PurchasePage clickCasualDressesButton() {
		return clickElement(casualDressesButton);
	}
	
	/**
	 * Presses a "Evening Dresses" button to move to evening dresses list
	 * @return the same page with evening dresses list content
	 */
	private PurchasePage clickEveningDressesButton() {
		return clickElement(eveningDressesButton);
	}
	
	/**
	 * Presses a "Summer Dresses" button to move to summer dresses list
	 * @return the same page with summer dresses list content
	 */
	private PurchasePage clickSummerDressesButton() {
		return clickElement(summerDressesButton);
	}
	
	/**
	 * Opens a page with Casual Dresses
	 * @return
	 */
	public PurchasePage goToCasualDressesList() {
		clickDressesButton().waitUntilElementPresent(casualDressesButton, 5);
		return clickCasualDressesButton();
	}
	
	/**
	 * Opens a page with Evening Dresses
	 * @return
	 */
	public PurchasePage goToEveningDressesList() {
		clickDressesButton().waitUntilElementPresent(eveningDressesButton, 5);
		return clickEveningDressesButton();
	}
	
	/**
	 * Opens a page with Summer Dresses
	 * @return
	 */
	public PurchasePage goToSummerDressesList() {
		clickDressesButton().waitUntilElementPresent(summerDressesButton, 5);
		return clickSummerDressesButton();
	}
	
	public PurchasePage clickTopsButton() {
		return clickElement(topsButton);
	}

	public int getNumberOfProductElements() {
		List<WebElement> list = getListOfElements(productList);
		return list.isEmpty() ? 0 : list.size();
	}
		
	public PurchasePage moveMouseOverFirstAvailableProduct() {
		List<WebElement> list = getListOfElements(productList);
		if (!list.isEmpty()) {
			new Actions(driver).moveToElement(list.get(0)).perform();
		}
		return this;
	}
	
	public boolean isAddToCartButtonDisplayed() {
		WebElement element = getElementIfExists(addToCartButton);
		return element.isDisplayed();
	}

	public PurchasePage clickAddToCartButton() {
		return clickElement(addToCartButton);
	}

	public boolean isContinueShoppingButtonDisplayed() {
		WebElement button = getElementIfExists(continueShoppingButton);
		waitUntilElementPresent(continueShoppingButton, 5);
		return button.isDisplayed();
	}

	public boolean isProceedToCheckoutButtonDisplayed() {
		WebElement button = getElementIfExists(proceedToCheckoutButton);
		waitUntilElementPresent(proceedToCheckoutButton, 5);
		return button.isDisplayed();
	}
	
	public ShoppingBasketPage clickProceedToCheckoutButton() {
		WebElement button = getElementIfExists(proceedToCheckoutButton);
		waitUntilElementPresent(proceedToCheckoutButton, 5);
		button.click();
		return new ShoppingBasketPage();
	}
	
}
