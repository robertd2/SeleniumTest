package com.project.selenium;

import com.project.selenium.purchase.ClothingType;

public interface IMainPage {

	void logInUser(User user);
	
	void buySomething(User user, ClothingType type, int numberOfItems);
}
