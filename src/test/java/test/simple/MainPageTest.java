package test.simple;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.project.selenium.MainPage;
import com.project.selenium.Setup;
import com.project.selenium.purchase.PurchasePage;

public class MainPageTest {
	
	private WebDriver driver;
	private MainPage mainPage;
	private PurchasePage purchasePage;
	
	@Before
    public void setUp() throws Exception {
		driver = Setup.getDriver();
		mainPage = new MainPage();
		purchasePage = new PurchasePage();
    }
	
	@After
    public void tearDown() throws Exception {
		Setup.quit();
    }
	
	@Test
	public void buySomeTShirt() throws Exception {
		purchasePage.clickOnTShirtBtn();
	}

	@Test
	public void navigationTabTest() throws Exception {
		mainPage.hoverWomenTab()
				.hoverDressTab()
				.hoverTshirtTab()
				.openWomenPageFromTab();
	}
}
