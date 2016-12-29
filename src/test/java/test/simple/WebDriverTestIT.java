package test.simple;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTestIT {
	
	private WebDriver driver;
	
	@Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "target/chromedriver.exe");
        driver = new ChromeDriver();
    }
	
	@After
    public void tearDown() throws Exception {
        driver.quit();
    }
	
	@Test
	public void doFirstSimpleTest() throws Exception {
		driver.get("http://automationpractice.com/index.php");
		
		String titleElement = driver.getTitle();
		
		assertEquals("My Store", titleElement);
	}

}
