package com.project.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ContactUsPage extends BasePage{

    private WebDriver driver;
    private static final By subjectHeadingDropDown = By.cssSelector("select#id_contact");
    private static final By emailAddressInput = By.cssSelector("input#email");
    private static final By orderReferenceInput = By.cssSelector("input#id_order");
    private static final By messageTextBox = By.cssSelector("textarea#message");
    private static final By sendButton = By.cssSelector("button#submitMessage");
    private static final By alertBox = By.cssSelector("div.alert-danger");
    private static final By alertBoxMessage = By.cssSelector("div.alert-danger ol>li");
    private static final By successBox = By.cssSelector("p.alert-success");
    private static final By subjectDescriptionField = By.cssSelector("p.desc_contact.contact-title");
    private static final By uploadFileInput = By.cssSelector("input#fileUpload");
    private static final By validatedPositiveEmailField = By.cssSelector("p.form-ok>input#email");
    private static final By validatedNegativeEmailField = By.cssSelector("p.form-error>input#email");

    public ContactUsPage(){
        driver = Setup.getDriver();
    }

    public ContactUsPage enterEmail(String mail){
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(mail);
        return this;
    }

    public ContactUsPage enterOrderReference(String orderReference){
        driver.findElement(orderReferenceInput).clear();
        driver.findElement(orderReferenceInput).sendKeys(orderReference);
        return this;
    }

    public ContactUsPage enterMessage(String messageText){
        driver.findElement(messageTextBox).clear();
        driver.findElement(messageTextBox).sendKeys(messageText);
        return this;
    }

    public ContactUsPage clickSendButton(){
        driver.findElement(sendButton).click();
        return this;
    }

    public ContactUsPage selectCustomerSubject(){
        Select dropdown = new Select(driver.findElement(subjectHeadingDropDown));
        dropdown.selectByValue("2");
        return this;
    }

    public ContactUsPage selectWebmasterSubject(){
        Select dropdown = new Select(driver.findElement(subjectHeadingDropDown));
        dropdown.selectByValue("1");
        return this;
    }

    public ContactUsPage checkAlert(String errorMessage){
        waitUntilElementPresent(alertBox,5);
        if (errorMessage != null) {
            String currentMessage = driver.findElement(alertBoxMessage).getText();
            assertTrue("Wrong error message Is: " + currentMessage + " Should be: " + errorMessage,
                    currentMessage.contains(errorMessage));
        }
        return this;
    }

    public ContactUsPage checkSuccessMessage(String successMessage){
        waitUntilElementPresent(successBox,5);
        if (successMessage != null) {
            String currentMessage = driver.findElement(successBox).getText();
            assertTrue("Wrong error message. Is: " + currentMessage + " Should be: " + successMessage,
                    currentMessage.contains(successMessage));
        }
        return this;
    }

    public ContactUsPage checkSubjectDescription(String subjectDescription){
        List<WebElement> listOfFields = driver.findElements(subjectDescriptionField);
        int index = 0;
        for (WebElement element : listOfFields){
            if (element.isDisplayed())
                index=listOfFields.indexOf(element);
        }
        String currentDescription = listOfFields.get(index).getText();
        assertTrue("Wrong subject description. Is: " + currentDescription + " Should be: " + subjectDescription,
                currentDescription.contains(subjectDescription));
        return this;
    }

    public ContactUsPage sendFile(String pathToFile){
        driver.findElement(uploadFileInput).sendKeys(pathToFile);
        return this;
    }

    public ContactUsPage selectSubjectAndPressTabKey(){
        selectCustomerSubject();
        driver.findElement(subjectHeadingDropDown).sendKeys(Keys.TAB);
        WebElement activeElement = driver.switchTo().activeElement();
        assertTrue("Wrong field is active. Should be " + emailAddressInput.toString()
                        + ". Butis  " + activeElement.toString(),
                driver.findElement(emailAddressInput).equals(activeElement));
        return this;
    }

    public ContactUsPage enterEmailAndPressTabKey(String email){
        enterEmail(email);
        driver.findElement(emailAddressInput).sendKeys(Keys.TAB);
        WebElement activeElement = driver.switchTo().activeElement();
        assertTrue("Wrong field is active. Should be " + orderReferenceInput.toString()
                        + ". But is " + activeElement.toString(),
                driver.findElement(orderReferenceInput).equals(activeElement));
        if (isCorrectEmail(email))
            assertTrue("Email field validation failed. Element should has class form-ok",
                    !driver.findElements(validatedPositiveEmailField).isEmpty());
        else
            assertTrue("Email field validation failed. Element should has class form-ok",
                    !driver.findElements(validatedNegativeEmailField).isEmpty());
        return this;
    }

    public ContactUsPage enterOrderReferenceAndPressTab(String orderReference) {
        enterOrderReference(orderReference);
        driver.findElement(orderReferenceInput).sendKeys(Keys.TAB);
        WebElement activeElement = driver.switchTo().activeElement();
        assertTrue("Wrong field is active. Should be " + uploadFileInput.toString()
                        + ". But is  " + activeElement.toString(),
                driver.findElement(uploadFileInput).equals(activeElement));
        return this;
    }

    public ContactUsPage sendFileAndPressTab(String path){
        sendFile(path);
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(orderReferenceInput),Keys.TAB).sendKeys(Keys.TAB).perform();
        WebElement activeElement = driver.switchTo().activeElement();
        assertTrue("Wrong field is active. Should be " + messageTextBox.toString()
                        + ". But is  " + activeElement.toString(),
                driver.findElement(messageTextBox).equals(activeElement));
        return this;
    }

    public ContactUsPage enterMessageAndPressTab(String message){
        enterMessage(message);
        driver.findElement(messageTextBox).sendKeys(Keys.TAB);
        WebElement activeElement = driver.switchTo().activeElement();
        assertTrue("Wrong field is active. Should be " + sendButton.toString()
                        + ". But is  " + activeElement.toString(),
                driver.findElement(sendButton).equals(activeElement));
        return this;
    }

    public ContactUsPage clickSendButtonByKey(){
        driver.findElement(sendButton).sendKeys(Keys.ENTER);
        return this;
    }

    private boolean isCorrectEmail(String emailAddress) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailPattern).matcher(emailAddress).matches();
    }
}
