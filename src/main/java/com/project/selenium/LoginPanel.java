package com.project.selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPanel {

	private WebDriver driver;
	private WebElement form;
	private WebElement emailTF;
	private WebElement passwdTF;
	private WebElement submitBTN;

	public LoginPanel() {
		driver = Setup.getDriver();
		assertTrue(isLoginFormOnPage(driver));
		setFields(driver);
	}

	public static boolean isLoginFormOnPage(WebDriver driver) {
		boolean result = false;
		try {
			WebElement form = driver.findElement(By.cssSelector("form#login_form"));
			if (form != null) {
				if (form.findElement(By.cssSelector("input#email")) != null
						&& form.findElement(By.cssSelector("input#passwd")) != null
						&& form.findElement(By.cssSelector("button#SubmitLogin")) != null) {
					result = true;
				}
			}
		} catch (NoSuchElementException e) {
			result = false;
		}
		return result;
	}

	public void setFields(WebDriver driver) {
		form = driver.findElement(By.cssSelector("form#login_form"));
		emailTF = form.findElement(By.cssSelector("input#email"));
		passwdTF = form.findElement(By.cssSelector("input#passwd"));
		submitBTN = form.findElement(By.cssSelector("button#SubmitLogin"));
	}

	public void loginUser(User user) {
		emailTF.clear();
		passwdTF.clear();
		emailTF.sendKeys(user.getEmail());
		passwdTF.sendKeys(user.getPassword());
		submitBTN.click();
	}

}
