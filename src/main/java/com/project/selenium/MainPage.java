package com.project.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.project.selenium.purchase.ClothingType;
import com.project.selenium.purchase.SinglePurchase;

public class MainPage implements IMainPage {
	
	private WebDriver driver;
	
	public MainPage(){
		driver = Setup.getDriver();
		Setup.loadMainPage();		
	}
	
	public void logInUser(User user) {
		// TODO Auto-generated method stub
	}

	public void buySomething(User user, ClothingType itemType, int numberOfItems) {
		SinglePurchase singlePurchase = new SinglePurchase(driver.findElement(By.id("block_top_menu")));
		
	}	

}
