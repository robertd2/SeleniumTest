package test.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.project.selenium.Setup;
import com.project.selenium.purchase.PurchasePage;
import com.project.selenium.purchase.ShoppingBasketPage;

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
    public void checkListOfTShirts() {
    	PurchasePage purchasePage = new PurchasePage();
    	int numberOfProducts = purchasePage.clickTShirtButton().getNumberOfProductElements();
    	assertTrue(numberOfProducts > 0);
    }
              
    @Test
    public void isAddToCartButtonAvailable() {
    	PurchasePage purchasePage = new PurchasePage();
    	boolean isAddToCartAvailable = purchasePage.clickTShirtButton()
				.moveMouseOverFirstAvailableProduct()
				.isAddToCartButtonDisplayed();
    	assertTrue("AddToCart should be available to click, but is: ", isAddToCartAvailable);    	
    }
            
    @Test
    public void buyFirstAvailableTShirt() {
    	PurchasePage purchasePage = new PurchasePage();
    	ShoppingBasketPage shoppingBasketPage = purchasePage.clickTShirtButton()
    			.moveMouseOverFirstAvailableProduct()
    			.clickAddToCartButton()
    			.clickProceedToCheckoutButton();
    	String confirmation = shoppingBasketPage.clickProceedToCheckoutButton()
    			.performLogIn("k782713@mvrht.com","12345")
    			.clickProceedToCheckoutButtonAtAddress()
    			.tickTermsCheckbox()
    			.clickProceedToCheckoutButtonAtShipping()
    			.clickBankWirePayment()
    			.clickConfirmOrder()
    			.getConfirmation();
    	assertEquals("I expected: \"Your order on My Store is complete.\", I received " 
    			+ confirmation + ", however.", "Your order on My Store is complete.", confirmation);
    }
    
    @Test
    public void checkListOfCasualDresses() {
    	PurchasePage purchasePage = new PurchasePage();
    	int numberOfProducts = purchasePage.goToCasualDressesList().getNumberOfProductElements();
    	assertEquals("Expected 1 product, found: " + numberOfProducts, 1, numberOfProducts);
    }
    
    @Test
    public void checkListOfEveningDresses() {
    	PurchasePage purchasePage = new PurchasePage();
    	int numberOfProducts = purchasePage.goToEveningDressesList().getNumberOfProductElements();
    	assertEquals("Expected 1 product, found: " + numberOfProducts, 1, numberOfProducts);
    }
    
    @Test
    public void checkListOfSummerDresses() {
    	PurchasePage purchasePage = new PurchasePage();
    	int numberOfProducts = purchasePage.goToSummerDressesList().getNumberOfProductElements();
    	assertEquals("Expected 3 products, found: " + numberOfProducts, 3, numberOfProducts);
    }
    
    @Test
    public void checkListOfAllDresses() {
    	PurchasePage purchasePage = new PurchasePage();
    	int numberOfProducts = purchasePage.clickDressesButton().getNumberOfProductElements();
    	assertEquals("Expected 5 products, found: " + numberOfProducts, 5, numberOfProducts);
    }

}
