package com.project.selenium;

public class MainPage extends BasePage {

    private String title = "My Store";

	public MainPage(){
		super();
		Setup.loadMainPage();
		verifyTitle(title);
	}

}
