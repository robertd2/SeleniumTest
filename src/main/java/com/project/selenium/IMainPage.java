package com.project.selenium;

public interface IMainPage {

	void logInUser(User user);
	
	void buySomething(User user, String itemType, int numberOfItems);
}
