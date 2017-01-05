package test.simple;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.project.selenium.BasePage;
import com.project.selenium.MainPage;
import com.project.selenium.RegisterPanel;
import com.project.selenium.Setup;
import com.project.selenium.User;

import test.reader.FileReader;
import test.reader.strategy.CsvReaderStrategy;

public class RegistrationTest {
	@BeforeClass
	public static void setUp() throws Exception {
		Setup.getDriver();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Setup.quit();
	}

	@Test
	public void registrationTest() {
		FileReader<User> reader = null;
		reader = new FileReader<User>("./target/exampledata.csv", new CsvReaderStrategy(";"), User.class);
		reader.setHeader(true);
		reader.loadFile();
		for (User user : reader.convertDataIntoListOfObjects()) {
			MainPage mainPage = new MainPage();
			RegisterPanel registerPanel = mainPage.gotoRegisterPanel();
			registerPanel.createAccount(user);
			assertEquals("Something went wrong after registration", "My account",
					Setup.getDriver()
							.findElement(By.cssSelector("#columns > div.breadcrumb.clearfix > span.navigation_page"))
							.getText());
			BasePage.waitUntilElementPresent(By.cssSelector("a[title='Log me out']"), 5, false);
			mainPage.logOut();
		}
	}
}
