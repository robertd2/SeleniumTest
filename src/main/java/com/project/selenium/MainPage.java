package com.project.selenium;

public class MainPage extends BasePage {


	public MainPage(){
		super();
		Setup.loadMainPage();
		verifyTitle("My Store");
	}

}
