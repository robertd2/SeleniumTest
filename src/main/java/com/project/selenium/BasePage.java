package com.project.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {
	
	public WebDriver driver;
	
	protected static final By womenMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"Women\"]");
	protected static final By dressMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"Dresses\"]");
	protected static final By tshirtMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"T-shirts\"]");
	protected static final By openedWomenMenuTab = By.cssSelector("ul.menu-content>li.sfHover>a[title=\"Women\"]");
	protected static final By openedDressMenuTab = By.cssSelector("ul.menu-content>li.sfHover>a[title=\"Dresses\"]");
	protected static final By singInButton = By.cssSelector("a[title='Log in to your customer account']");
	protected static final By singOutButton = By.cssSelector("a[title='Log me out']");
	protected static final By contactUsLink = By.cssSelector("a[title='Contact Us']");
		
	public BasePage(){
		driver = Setup.getDriver();
	}
	
	public void verifyTitle(String title){
		assertEquals(title,driver.getTitle());
	}
	
	public void logIn(User user) {
		if(driver.findElements(singInButton).size()==0){
			System.out.println("Can't log in. Maybe you are already logged?");
		}else{
		clickSingInBtn();
		LoginPanel loginPanel = new LoginPanel();
		loginPanel.loginUser(user);
		}
	}

	public void logOut(){
		if(driver.findElements(singOutButton).size()==0){
			System.out.println("Can't logout. Are you logged?");
		}else{
		driver.findElement(singOutButton).click();
		}
	}	
	

	public BasePage hoverWomenTab(){
		WebElement womenMenuButton = driver.findElement(womenMenuTab);
		new Actions(driver).moveToElement(womenMenuButton)
				.perform();
		waitUntilElementPresent(openedWomenMenuTab,5);

		return this;
	}

	public BasePage hoverDressTab(){
		WebElement dressMenuButton = driver.findElement(dressMenuTab);
		new Actions(driver).moveToElement(dressMenuButton)
				.perform();
		waitUntilElementPresent(openedDressMenuTab,5);

		return this;
	}

	public BasePage hoverTshirtTab(){
		WebElement tshirtMenuButton = driver.findElement(tshirtMenuTab);
		new Actions(driver).moveToElement(tshirtMenuButton)
				.perform();

		return this;
	}

	public WomanPage openWomenPageFromTab(){
		WebElement womenMenuButton = driver.findElement(womenMenuTab);
		new Actions(driver).moveToElement(womenMenuButton).click()
				.perform();
		return new WomanPage();
	}
	
	public void waitUntilElementPresent(By byCss, int timeInSec){
		List<WebElement> elements = driver.findElements(byCss);
		int timeOut=0;
		boolean displayed = false;

		if (!elements.isEmpty())
			displayed = elements.get(0).isDisplayed();

		while(!displayed && timeOut<timeInSec){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeOut++;
			elements = driver.findElements(byCss);
			if (!elements.isEmpty())
				displayed = elements.get(0).isDisplayed();
		}
		assertTrue("Element not found", displayed);
	}
	
	public void clickSingInBtn(){
		driver.findElement(singInButton).click();
	}

	public ContactUsPage clickContactUsBtn() {
		driver.findElement(contactUsLink).click();
		return new ContactUsPage();
	}
	
}
