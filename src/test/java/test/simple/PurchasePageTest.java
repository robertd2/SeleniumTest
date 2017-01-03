package test.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project.selenium.Setup;
import com.project.selenium.purchase.PurchasePage;

public class PurchasePageTest {
	
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
    	purchasePage.clickOnTShirtBtn();
    	assertTrue("T-SHIRTS".equals(purchasePage.getProductListTitle().trim()));
    }
    
    @Test
    public void checkIfTShirtListNotEmpty() {
    	PurchasePage purchasePage = new PurchasePage();
    	purchasePage.clickOnTShirtBtn();
    	assertTrue(purchasePage.getNumberOfTShirtElements() > 0);
    }
    
    @Test
    public void isFirstProductAvailable() {
    	PurchasePage purchasePage = new PurchasePage();
    	assertTrue(purchasePage.clickOnTShirtBtn().isFirstProductAvailable());
    }
    
    @Test
    public void showFirstAvailableProduct() {
    	PurchasePage purchasePage = new PurchasePage();
    	purchasePage.getFirstAvailableProduct();
    }
    
    @Test
    public void isContinueShoppingButtonAvailable() {
    	PurchasePage purchasePage = new PurchasePage();
    	purchasePage.clickOnTShirtBtn();
    	if (purchasePage.getNumberOfTShirtElements() > 0) {
    		purchasePage.moveMouseOverFirstAvailableProduct()
    					.clickAddToCartButton();     		
    		assertTrue(purchasePage.checkIfContinueShoppingButtonsIsPresent() > 0);
    		assertEquals(purchasePage.checkIfContinueShoppingButtonsIsPresent(),1);
    		assertTrue(purchasePage.isContinueShoppingButtonVisible());
    	}
    }

}
