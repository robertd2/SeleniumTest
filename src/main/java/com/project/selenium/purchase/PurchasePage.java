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

	public PurchasePage() {
		super();
	}
	
	protected PurchasePage clickElement(By element) {
		WebElement webElement = getElementIfExists(element);
		webElement.click();
		return this;
	}
	
	public PurchasePage clickTShirtButton() {
		return clickElement(tshirtMenuTab);
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
