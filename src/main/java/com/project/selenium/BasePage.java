package com.project.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {

	public static WebDriver driver;

	protected static final By womenMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"Women\"]");
	protected static final By dressMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"Dresses\"]");
	protected static final By tshirtMenuTab = By.cssSelector("ul.menu-content>li>a[title=\"T-shirts\"]");
	protected static final By openedWomenMenuTab = By.cssSelector("ul.menu-content>li.sfHover>a[title=\"Women\"]");
	protected static final By openedDressMenuTab = By.cssSelector("ul.menu-content>li.sfHover>a[title=\"Dresses\"]");
	protected static final By singInButton = By.cssSelector("a[title='Log in to your customer account']");
	protected static final By singOutButton = By.cssSelector("a[title='Log me out']");
	protected static final By contactUsLink = By.cssSelector("a[title='Contact Us']");
	// search box
	protected static final By searchbox = By.cssSelector("input#search_query_top");
	protected static final By submitSearchbutton = By.cssSelector("button[name='submit_search']");
	protected static final By searchSuggestionBox = By.cssSelector("div.ac_results");
	protected static final By searchSuggestionFirstElement = By.cssSelector("div.ac_results>ul>li:first-child");

	public BasePage() {
		driver = Setup.getDriver();
	}

	public void verifyTitle(String title) {
		assertEquals(title, driver.getTitle());
	}
	
	protected WebElement getElementIfExists(By element) {
		List<WebElement> elementList = driver.findElements(element);
		WebElement result = null;
		if (!elementList.isEmpty() && elementList.size() == 1) {
			result = elementList.get(0);
		}
		return result;
	}
	
	protected List<WebElement> getListOfElements(By element) {
		List<WebElement> elementList = driver.findElements(element);
		return elementList;
	}

	public void logIn(User user) {
		assertTrue("Can't log in. Maybe you are already logged?", driver.findElements(singInButton).size() == 1);

		LoginPanel loginPanel = clickSingInBtn();
		loginPanel.loginUser(user);

	}

	public void logOut() {
		assertTrue("Can't logout. Are you logged?", driver.findElements(singOutButton).size() == 1);
		driver.findElement(singOutButton).click();

	}

	public RegisterPanel gotoRegisterPanel() {
		if (!(driver.findElements(singOutButton).size() == 0)) {
			logOut();
		}
		driver.findElement(singInButton).click();
		return new RegisterPanel();
	}

	public BasePage hoverWomenTab() {
		WebElement womenMenuButton = driver.findElement(womenMenuTab);
		new Actions(driver).moveToElement(womenMenuButton).perform();
		waitUntilElementPresent(openedWomenMenuTab, 5);
		return this;
	}

	public BasePage hoverDressTab() {
		WebElement dressMenuButton = driver.findElement(dressMenuTab);
		new Actions(driver).moveToElement(dressMenuButton).perform();
		waitUntilElementPresent(openedDressMenuTab, 5);
		return this;
	}

	public BasePage hoverTshirtTab() {
		WebElement tshirtMenuButton = driver.findElement(tshirtMenuTab);
		new Actions(driver).moveToElement(tshirtMenuButton).perform();
		return this;
	}

	public WomanPage openWomenPageFromTab() {
		WebElement womenMenuButton = driver.findElement(womenMenuTab);
		new Actions(driver).moveToElement(womenMenuButton).click().perform();
		return new WomanPage();
	}

	public static void waitUntilElementPresent(By byCss, int timeInSec) {
		waitUntilElementPresent(byCss, timeInSec, true);
	}

	public static void waitUntilElementPresent(By byCss, int timeInSec, boolean check) {
		List<WebElement> elements = driver.findElements(byCss);
		int timeOut = 0;
		boolean displayed = false;

		if (!elements.isEmpty())
			displayed = elements.get(0).isDisplayed();

		while (!displayed && timeOut < timeInSec) {
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
		if (check) {
			assertTrue("Element not found", displayed);
		}

	}

	public LoginPanel clickSingInBtn() {
		driver.findElement(singInButton).click();
		return new LoginPanel();
	}

	public ContactUsPage clickContactUsBtn() {
		driver.findElement(contactUsLink).click();
		return new ContactUsPage();
	}

	public void searchProduct(String productName) {
		driver.findElement(searchbox).clear();
		driver.findElement(searchbox).sendKeys(productName);
	}

	public SearchPage clickSearchButton() {
		driver.findElement(submitSearchbutton).click();
		return new SearchPage();
	}

	public SearchPage confirmSearchByEnterKey() {
		driver.findElement(searchbox).sendKeys(Keys.ENTER);
		return new SearchPage();
	}

	public SearchPage searchProductAndConfirm(String productName) {
		searchProduct(productName);
		return confirmSearchByEnterKey();
	}

	public void verifySearchSuggestion(String searchedProduct) {
		waitUntilElementPresent(searchSuggestionBox, 5);
		String firstElement = driver.findElement(searchSuggestionFirstElement).getText().toLowerCase();
		assertTrue("First element (" + firstElement + ") doesn't contain entered text: " + searchedProduct,
				firstElement.contains(searchedProduct));
	}

	public SearchPage openFirstElementFromSuggestionBox() {
		waitUntilElementPresent(searchSuggestionBox, 5);
		String firstElement = driver.findElement(searchSuggestionFirstElement).getText();
		String nameOfFirstElement = firstElement.substring(firstElement.indexOf('>') + 2);
		driver.findElement(searchSuggestionFirstElement).click();
		return new SearchPage(nameOfFirstElement);
	}

}
