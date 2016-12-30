package com.project.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.project.selenium.purchase.ClothingType;
import com.project.selenium.purchase.SinglePurchase;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MainPage implements IMainPage {
	
	private WebDriver driver;

	private static final By womenMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"Women\"]");
	private static final By dressMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"Dresses\"]");
	private static final By tshirtMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"T-shirts\"]");
	private static final By openedWomenMenuTab = By.cssSelector("ul.menu-content>li.sfHover>a[title=\"Women\"]");
	private static final By openedDressMenuTab = By.cssSelector("ul.menu-content>li.sfHover>a[title=\"Dresses\"]");

	public MainPage(){
		driver = Setup.getDriver();
		Setup.loadMainPage();
	}
	
	public void logInUser(User user) {
		// TODO Auto-generated method stub
	}

	public MainPage hoverWomenTab(){
		WebElement womenMenuButton = driver.findElement(womenMenuTab);
		new Actions(driver).moveToElement(womenMenuButton)
				.perform();
		waitUntilElementPresent(openedWomenMenuTab,5);

		return this;
	}

	public MainPage hoverDressTab(){
		WebElement dressMenuButton = driver.findElement(dressMenuTab);
		new Actions(driver).moveToElement(dressMenuButton)
				.perform();
		waitUntilElementPresent(openedDressMenuTab,5);

		return this;
	}

	public MainPage hoverTshirtTab(){
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

	public void buySomething(User user, ClothingType itemType, int numberOfItems) {
		SinglePurchase singlePurchase = new SinglePurchase(driver.findElement(By.id("block_top_menu")));

	}

	public void waitUntilElementPresent(By byCss, int timeInSec){
		List<WebElement> elements = driver.findElements(byCss);
		int timeOut = 0;
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
}
