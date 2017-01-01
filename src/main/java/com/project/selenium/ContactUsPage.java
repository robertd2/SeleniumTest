package com.project.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
        assertTrue("Wrong subject description. Is: " + currentDescription + " Shuold be: " + subjectDescription,
                currentDescription.contains(subjectDescription));
        return this;
    }

    public ContactUsPage sendFile(String pathToFile){
        driver.findElement(uploadFileInput).sendKeys(pathToFile);
        return this;
    }
}
