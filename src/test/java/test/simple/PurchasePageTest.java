package test.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.project.selenium.Setup;
import com.project.selenium.purchase.PurchasePage;

public class PurchasePageTest {
	
	private static final By addItemToCartWindow = By.cssSelector("div#layer_cart > div.clearfix");
	
	@BeforeClass
    public static void setUp() throws Exception {
        Setup.getDriver();        
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Setup.quit();
    }
    
    @Test
    public void checkTShirtTab() {
    	PurchasePage purchasePage = new PurchasePage();
    	assertTrue(purchasePage.checkIfTShirtTabExists());
    }
    
    @Test
    public void checkIfListOfTShirtsShows() {
    	PurchasePage purchasePage = new PurchasePage();    	
    	assertTrue("T-SHIRTS".equals(purchasePage.clickOnTShirtBtn().getProductListTitle().trim()));
    }
    
    @Test
    public void checkIfTShirtListNotEmpty() {
    	PurchasePage purchasePage = new PurchasePage();    	
    	assertTrue(purchasePage.clickOnTShirtBtn().getNumberOfTShirtElements() > 0);
    }
    
    @Test
    public void isFirstProductAvailable() {
    	PurchasePage purchasePage = new PurchasePage();
    	assertTrue(purchasePage.clickOnTShirtBtn().isFirstProductAvailable());
    }
    
    @Test
    public void showFirstAvailableProduct() {
    	PurchasePage purchasePage = new PurchasePage();
    	WebElement fAvProd = purchasePage.clickOnTShirtBtn().getFirstAvailableProduct();
    	System.out.println(fAvProd.getText());
    }
    
    @Test
    public void isAddToCartButtonAvailable() {
    	PurchasePage purchasePage = new PurchasePage();
    	assertTrue("AddToCart is available to click", purchasePage.clickOnTShirtBtn()
    				.moveMouseOverFirstAvailableProduct()
    				.isAddToCartButtonDisplayed());    	
    }
    
    @Test
    public void isCheckoutAvailable() {
    	PurchasePage purchasePage = new PurchasePage();
    	assertTrue("ProceedToCheckout is available to click", purchasePage.clickOnTShirtBtn()
    			.moveMouseOverFirstAvailableProduct()
    			.clickAddToCartButton()    			
    			.isProceedToCheckoutButtonDisplayed());
    }

}
