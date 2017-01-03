package test.simple;
import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.project.selenium.LoginPanel;
import com.project.selenium.MainPage;
import com.project.selenium.Setup;
import com.project.selenium.User;

public class LoginTest {
	
	protected static final By errorMsg = By.cssSelector("#center_column > div.alert.alert-danger > ol > li");
	
	@BeforeClass
    public static void setUp() throws Exception {
		Setup.getDriver();
    }
	
	@AfterClass
    public static void tearDown() throws Exception {
		Setup.quit();
    }
	
	@Test
	public void isLoginPanelTest() throws Exception {
		new MainPage();
		WebDriver driver = Setup.getDriver();
		
		assertFalse((boolean)LoginPanel.isLoginFormOnPage(driver));
	
	}
	
	@Test
	public void isLoginPanelTest2() throws Exception {
		MainPage mainPage=new MainPage();
		WebDriver driver = Setup.getDriver();
		
		mainPage.clickSingInBtn();
		assertTrue((boolean)LoginPanel.isLoginFormOnPage(driver));
	
	}
	
	@Test
	public void logInAndLogOutUserTest() throws Exception {
		MainPage mainPage=new MainPage();
		WebDriver driver = Setup.getDriver();
		
		assertEquals(1,driver.findElements(By.cssSelector("a[title='Log in to your customer account']")).size());
		
		mainPage.logIn(new User("k782713@mvrht.com","12345"));
		assertEquals(1,driver.findElements(By.cssSelector("a[title='Log me out']")).size());
		
		mainPage.logOut();
		assertEquals(1,driver.findElements(By.cssSelector("a[title='Log in to your customer account']")).size());	
	
	}
	
	@Test
	public void invalidLoginTest() throws Exception{
		MainPage mainPage=new MainPage();
		WebDriver driver = Setup.getDriver();		
		
		mainPage.logIn(new User("k782713@mvrht.co","12345"));
		assertEquals("Expected error message: Authentication failed. It was: "+driver.findElement(errorMsg).getText(),"Authentication failed.",driver.findElement(errorMsg).getText());
		
		mainPage.logIn(new User("k782713@mvrht.com","1234"));
		assertEquals("Expected error message: Invalid password. It was: "+driver.findElement(errorMsg).getText(),"Invalid password.",driver.findElement(errorMsg).getText());
		
	}
	

}
