package com.project.selenium.purchase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.project.selenium.MainPage;

public class PurchasePage extends MainPage {

	private static final By productListHeading = By.cssSelector("span.cat-name");
	private static final By productList = By.cssSelector("ul.product_list div.product-container");
	private static final By productAvailability = By.cssSelector("ul.product_list span.available-now");
	private static final By addToCartButton = By.cssSelector("div.button-container > a.ajax_add_to_card_button");
	private static final By continueShoppingButton = By.cssSelector("div.button-container span.continue span");
	private static final By proceedToCheckoutButton = By.cssSelector("div.button-container a.btn[title='Proceed to checkout']");
	
	public PurchasePage() {
		super();
	}
	
	@Override
	public void clickOnTShirtBtn() {
		return tshirtMenuButton == null ? false : true;
	}
	
	public String getProductListTitle() {
		WebElement productListTitle = driver.findElement(productListHeading);
		return productListTitle == null ? "" : productListTitle.getText();
	}
	
	public PurchasePage clickOnTShirtBtn() {
		return this;
	}
	
	public int getNumberOfTShirtElements() {
		List<WebElement> list = driver.findElements(productList);
		return list.isEmpty() ? 0 : list.size();
	}
	
	public boolean isFirstProductAvailable() {
		List<WebElement> list = driver.findElements(productList);
		String result = null;
		
		if (!list.isEmpty()) {
			WebElement product = list.get(0).findElement(productAvailability);
			result = product.getText();
		}
		return result == null ? false : true;
	}
	
	public WebElement getFirstAvailableProduct() {
		List<WebElement> list = driver.findElements(productList);
		WebElement result = null;
		if (!list.isEmpty()) {
			for (WebElement element : list) {
				System.out.println(element.getTagName() + " : " + element.getText());
				WebElement availability = element.findElement(productAvailability);
				System.out.println(availability.getText());
				if (availability.getText().equals("In stock")) {
					result = element;
				}
			}
		}
		return result;
	}
	
	public PurchasePage moveMouseOverFirstAvailableProduct() {
		List<WebElement> list = driver.findElements(productList);
		if (!list.isEmpty()) {
			new Actions(driver).moveToElement(list.get(0));
		}
		return this;
	}
	
	public void clickAddToCartButton() {
		WebElement addToCard = driver.findElement(addToCartButton);
		addToCard.click();
		driver.switchTo().activeElement();		
	}
	
	public int checkIfContinueShoppingButtonsIsPresent() {
		List<WebElement> list = driver.findElements(continueShoppingButton);
		return list.size();
	}
	
	public boolean isContinueShoppingButtonVisible() {
		WebElement button = driver.findElement(continueShoppingButton);
		return button.isDisplayed();
}
