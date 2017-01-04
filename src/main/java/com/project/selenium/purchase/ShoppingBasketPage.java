package com.project.selenium.purchase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.project.selenium.BasePage;
import com.project.selenium.LoginPanel;
import com.project.selenium.User;

public class ShoppingBasketPage extends BasePage {

	private static final String title = "Order - My Store";
	private static final By proceedToCheckoutButtonSummary = By.cssSelector("p.cart_navigation a.btn[title='Proceed to checkout']");
	private static final By proceedToCheckoutButtonAddress = By.cssSelector("p.cart_navigation button.button[name='processAddress']");
	private static final By proceedToCheckoutButtonShipping = By.cssSelector("p.clearfix button.button");
	private static final By termsCheckbox = By.cssSelector("p.checkbox input#cgv");
	private static final By payByBankWire = By.cssSelector("div#HOOK_PAYMENT p.payment_module a.bankwire");
	private static final By confirmOrderButton = By.cssSelector("p#cart_navigation button.button");
	private static final By confirmationText = By.cssSelector("p.cheque-indent strong.dark");
	
	public ShoppingBasketPage() {
		verifyTitle(title);
	}
	
	protected ShoppingBasketPage clickElement(By element) {
		WebElement webElement = getElementIfExists(element);
		webElement.click();
		return this;
	}
	
	protected ShoppingBasketPage selectElement(By element) {
		WebElement webElement = getElementIfExists(element);
		if(!webElement.isSelected()) {
			webElement.click();
		}
		return this;
	}
	
	public ShoppingBasketPage clickProceedToCheckoutButton() {
		return clickElement(proceedToCheckoutButtonSummary);
	}
	
	public ShoppingBasketPage clickProceedToCheckoutButtonAtAddress() {
		return clickElement(proceedToCheckoutButtonAddress);
	}
	
	public ShoppingBasketPage clickProceedToCheckoutButtonAtShipping() {
		return clickElement(proceedToCheckoutButtonShipping);
	}
	
	public ShoppingBasketPage performLogIn(String email, String password) {
		LoginPanel panel = new LoginPanel();
		panel.loginUser(new User(email, password));
		return this;
	}	
	
	public ShoppingBasketPage tickTermsCheckbox() {
		return selectElement(termsCheckbox);
	}	
	
	public ShoppingBasketPage clickBankWirePayment() {
		return clickElement(payByBankWire);
	}
	
	public ShoppingBasketPage clickConfirmOrder() {
		return clickElement(confirmOrderButton);
	}
	
	public String getConfirmation() {
		WebElement confirmation = getElementIfExists(confirmationText);
		String result = null;
		if (confirmation != null) {
			result = confirmation.getText();
		}
		return result;
	}
}
