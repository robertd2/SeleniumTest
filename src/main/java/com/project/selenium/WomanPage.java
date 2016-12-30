package com.project.selenium;

import com.project.selenium.MainPage;
import com.project.selenium.Setup;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class WomanPage {

    private String title = "Women - My Store";
    private WebDriver driver;

    public WomanPage(){
        driver = Setup.getDriver();
        checkTitle();
    }

    private void checkTitle(){
        assertTrue("Wrong title",driver.getTitle().equals(title));
    }
}
