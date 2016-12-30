package com.project.selenium;

import org.openqa.selenium.WebDriver;

public class MainPage implements IMainPage {
	
	private WebDriver driver;
	
	public MainPage(){
		driver =Setup.getDriver();
	}
	
	public void logInUser(User user) {
		// TODO Auto-generated method stub
	}
	


	public void buySomething(User user, String itemType, int numberOfItems) {
		// TODO Auto-generated method stub
	}	

	

}
