package test.simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.project.selenium.MainPage;
import com.project.selenium.Setup;

public class MainPageTest {

	@SuppressWarnings("unused")
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
	public void navigationTabTest() throws Exception {
		mainPage.hoverWomenTab().hoverDressTab().hoverTshirtTab().openWomenPageFromTab();
	}
}
