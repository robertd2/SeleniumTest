package test.simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.project.selenium.MainPage;
import com.project.selenium.Setup;

public class MainPageTest {
	
	private WebDriver driver;
	private MainPage mainPage;
	
	@Before
    public void setUp() throws Exception {
		driver = Setup.getDriver();
		mainPage = new MainPage();
    }
	
	@After
    public void tearDown() throws Exception {
		Setup.quit();
    }
	
	@Test
	public void doMainPageTest() throws Exception {
		WebElement bodyContent = driver.findElement(By.xpath("html"));
	}
	
	@Test
	public void buySomeTShirt() throws Exception {
		
	}

	@Test
	public void navigationTabTest() throws Exception {
		WebDriver driver = Setup.getDriver();
		MainPage mainPage=new MainPage();
		mainPage.hoverWomenTab()
				.hoverDressTab()
				.hoverTshirtTab()
				.openWomenPageFromTab();
	}
}
