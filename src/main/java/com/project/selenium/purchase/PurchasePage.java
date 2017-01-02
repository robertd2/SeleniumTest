package com.project.selenium.purchase;

import org.openqa.selenium.WebElement;

import com.project.selenium.MainPage;

public class PurchasePage extends MainPage {

	public PurchasePage() {
		super();
	}
	
	@Override
	public void clickOnTShirtBtn() {
		WebElement tshirtMenuButton = driver.findElement(tshirtMenuTab);
		tshirtMenuButton.click();
	}
}
