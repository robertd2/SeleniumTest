package com.project.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Setup {

	private static WebDriver driver;
	
	public static WebDriver getDriver(){
		if(driver==null){
			System.setProperty("webdriver.chrome.driver", "target/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
		}
		return driver;
	}
	
	public static void loadMainPage(){
		getDriver();
		driver.get("http://automationpractice.com/");
	}
	
	public static void quit(){
		driver.quit();
	}
}
