package com.project.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPanel {

	private WebDriver driver;
	private WebElement createAccountForm;
	private WebElement createAccountEmailTF;
	private WebElement submitCreateBTN;
	private WebElement accountCreationForm;

	private WebElement mr;
	private WebElement mrs;
	private WebElement firstName;
	private WebElement lastName;
	private WebElement email;
	private WebElement password;
	private Select days;
	private Select months;
	private Select years;
	private WebElement newsletter;
	private WebElement partners;
	private WebElement adrFirstName;
	private WebElement adrLastName;
	private WebElement company;
	private WebElement address1;
	private WebElement address2;
	private WebElement city;
	private Select state;
	private WebElement zipCode;
	private Select country;
	private WebElement addInfo;
	private WebElement phone;
	private WebElement mobile;
	private WebElement addrAlias;
	private WebElement register;

	// initForm
	private static final By createForm = By.cssSelector("form#create-account_form");
	private static final By emialInput = By.cssSelector("input#email_create");
	private static final By createBtn = By.cssSelector("button#SubmitCreate");
	private static final By createAccountError = By.cssSelector("div#create_account_error");

	// creationForm
	private static final By creationForm = By.cssSelector("form#account-creation_form");
	// Personal Info
	private static final By MrRadioBtn = By.cssSelector("input#id_gender1");
	private static final By MrsRadioBtn = By.cssSelector("input#id_gender2");
	private static final By firstNameInput = By.cssSelector("input#customer_firstname");
	private static final By lastNameInput = By.cssSelector("input#customer_lastname");
	private static final By emailInput = By.cssSelector("input#email");
	private static final By passwordInput = By.cssSelector("input#passwd");
	private static final By daysSelect = By.cssSelector("select#days");
	private static final By monthsSelect = By.cssSelector("select#months");
	private static final By yearsSelect = By.cssSelector("select#years");
	private static final By newsletterCB = By.cssSelector("input#newsletter");
	private static final By partnersCB = By.cssSelector("input#optin");
	// Address
	private static final By addrFirstNameInput = By.cssSelector("input#firstname");
	private static final By addrLastNameInput = By.cssSelector("input#lastname");
	private static final By companyInput = By.cssSelector("input#company");
	private static final By address1Input = By.cssSelector("input#address1");
	private static final By address2Input = By.cssSelector("input#address2");
	private static final By cityInput = By.cssSelector("input#city");
	private static final By zipCodeInput = By.cssSelector("input#postcode");
	private static final By stateSelect = By.cssSelector("select#id_state");
	private static final By countrySelect = By.cssSelector("select#id_country");
	private static final By additionalInfoTA = By.cssSelector("textarea#other");
	private static final By phoneInput = By.cssSelector("input#phone");
	private static final By mobileInput = By.cssSelector("input#phone_mobile");
	private static final By addrAliasInput = By.cssSelector("input#alias");

	private static final By registerBtn = By.cssSelector("button#submitAccount");

	RegisterPanel() {
		driver = Setup.getDriver();
		assertTrue(isCreateAccountFormOnPage(driver));
		setInitialFields(driver);
	}

	public static boolean isCreateAccountFormOnPage(WebDriver driver) {
		boolean result = false;
		try {
			WebElement form = driver.findElement(createForm);
			if (form != null) {
				if (form.findElement(emialInput) != null && form.findElement(createBtn) != null) {
					result = true;
				}
			}
		} catch (NoSuchElementException e) {
			result = false;
		}
		return result;
	}

	public void setInitialFields(WebDriver driver) {
		createAccountForm = driver.findElement(createForm);
		createAccountEmailTF = createAccountForm.findElement(emialInput);
		submitCreateBTN = createAccountForm.findElement(createBtn);
	}

	public void setCreationFields() {
		accountCreationForm = driver.findElement(creationForm);
		mr = accountCreationForm.findElement(MrRadioBtn);
		mrs = accountCreationForm.findElement(MrsRadioBtn);
		firstName = accountCreationForm.findElement(firstNameInput);
		lastName = accountCreationForm.findElement(lastNameInput);
		email = accountCreationForm.findElement(emailInput);
		password = accountCreationForm.findElement(passwordInput);
		days = new Select(accountCreationForm.findElement(daysSelect));
		months = new Select(accountCreationForm.findElement(monthsSelect));
		years = new Select(accountCreationForm.findElement(yearsSelect));
		newsletter = accountCreationForm.findElement(newsletterCB);
		partners = accountCreationForm.findElement(partnersCB);
		adrFirstName = accountCreationForm.findElement(addrFirstNameInput);
		adrLastName = accountCreationForm.findElement(addrLastNameInput);
		company = accountCreationForm.findElement(companyInput);
		address1 = accountCreationForm.findElement(address1Input);
		address2 = accountCreationForm.findElement(address2Input);
		city = accountCreationForm.findElement(cityInput);
		state = new Select(accountCreationForm.findElement(stateSelect));
		zipCode = accountCreationForm.findElement(zipCodeInput);
		country = new Select(accountCreationForm.findElement(countrySelect));
		addInfo = accountCreationForm.findElement(additionalInfoTA);
		phone = accountCreationForm.findElement(phoneInput);
		mobile = accountCreationForm.findElement(mobileInput);
		addrAlias = accountCreationForm.findElement(addrAliasInput);
		register = accountCreationForm.findElement(registerBtn);
	}

	public void createAccount(User user) {
		createAccountEmailTF.clear();
		createAccountEmailTF.sendKeys(user.getEmail());
		submitCreateBTN.click();
		BasePage.waitUntilElementPresent(creationForm, 2, false);
		if (driver.findElements(createAccountError).size() == 1) {
			fail("Error: " + driver.findElement(createAccountError).findElement(By.cssSelector("ol > li")).getText());
		}
		assertTrue("Registration input form missing", driver.findElements(creationForm).size() == 1);
		setCreationFields();

		if (user.getGender().equals(User.Sex.Male)) {
			mr.click();
		} else {
			mrs.click();
		}
		firstName.sendKeys(user.getFirstName());
		lastName.sendKeys(user.getLastName());
		email.clear();
		email.sendKeys(user.getEmail());
		password.sendKeys(user.getPassword());
		days.selectByIndex(user.getBirthDate().getDayOfMonth());
		months.selectByIndex(user.getBirthDate().getMonthValue());
		years.selectByValue(new Integer(user.getBirthDate().getYear()).toString());
		adrFirstName.clear();
		adrFirstName.sendKeys(user.getFirstName());
		adrLastName.clear();
		adrLastName.sendKeys(user.getLastName());
		address1.sendKeys(user.getAddress());
		city.sendKeys(user.getCity());
		try {
			state.selectByVisibleText(user.getState());
		} catch (NoSuchElementException e) {
			state.selectByIndex(0);
		}
		zipCode.sendKeys(user.getZipCode());
		try {
			country.selectByVisibleText(user.getCountry());
		} catch (NoSuchElementException e) {
			country.selectByIndex(0);
		}
		mobile.sendKeys(user.getMobile());
		addrAlias.clear();
		addrAlias.sendKeys(user.getAlias());
		register.click();
	}

}
