package test.simple;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.project.selenium.Setup;

public class SetupTest {
	
	private WebDriver driver;
	
	@Before
    public void setUp() throws Exception {

    }
	
	@After
    public void tearDown() throws Exception {

    }
	
	@Test
	public void doFirstSimpleTest() throws Exception {
		Setup.loadMainPage();
		driver = Setup.getDriver();
		String titleElement = driver.getTitle();
		
		assertEquals("My Store", titleElement);
		
		Setup.quit();
	}

}
