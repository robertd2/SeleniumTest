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

	private static final By loginForm = By.cssSelector("form#login_form");
	private static final By emialInput = By.cssSelector("input#email");
	private static final By passInput = By.cssSelector("input#passwd");
	private static final By submitBtn = By.cssSelector("button#SubmitLogin");

	public LoginPanel() {
		driver = Setup.getDriver();
		assertTrue(isLoginFormOnPage(driver));
		setFields(driver);
	}

	public static boolean isLoginFormOnPage(WebDriver driver) {
		boolean result = false;
		try {
			WebElement form = driver.findElement(loginForm);
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
		form = driver.findElement(loginForm);
		emailTF = form.findElement(emialInput);
		passwdTF = form.findElement(passInput);
		submitBTN = form.findElement(submitBtn);
	}

	public void loginUser(User user) {
		emailTF.clear();
		passwdTF.clear();
		emailTF.sendKeys(user.getEmail());
		passwdTF.sendKeys(user.getPassword());
		submitBTN.click();
	}

}
