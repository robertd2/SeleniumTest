package test.simple;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.Assert.assertEquals;

public class LocateMethodsTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "target/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    
    @Test
    public void byPartialLinkTextTest() throws Exception {

        WebElement element;        
        element = driver.findElement(By.partialLinkText("Chiffon"));        
        assertEquals("Printed Chiffon Dress",element.getText());
    }
    
    @Test
    public void byClassNameTest() throws Exception {

        WebElement element;                   
        element = driver.findElement(By.className("logo"));        
        assertEquals("My Store",element.getAttribute("alt"));
    }
    
    @Test
    public void byIdTest() throws Exception {

        WebElement element;             
        element = driver.findElement(By.id("search_query_top"));        
        assertEquals("Search",element.getAttribute("placeholder"));   
        }    
   
    @Test
    public void byNameTest() throws Exception {

        WebElement element;             
        element = driver.findElement(By.name("submit_search"));        
        assertEquals("button",element.getTagName());   
        } 
    
    @Test
    public void byTagNameTest() throws Exception {

        WebElement element;             
        element = driver.findElement(By.tagName("h1"));        
        assertEquals("Automation Practice Website",element.getText());   
        }  
    
    @Test
    public void byCssSelectorTest() throws Exception {

        WebElement element;             
        //by class
        element = driver.findElement(By.cssSelector("a.btn.btn-default[href*='selenium']"));        
        assertEquals("Selenium Framework",element.getText());
        //by id
        element = driver.findElement(By.cssSelector("form#searchbox"));        
        assertEquals("get",element.getAttribute("method"));        
        
        } 
    
    @Test
    public void byXpathTest() throws Exception {

        WebElement element; 
        element = driver.findElement(By.xpath("//*[@id='cmsinfo_block']/div[1]/ul/li[2]/div/p/a"));        
        assertEquals("Selenium Framework",element.getText());  
        
        //by class 
        element = driver.findElement(By.xpath("//div[contains(@class,'type-text')]/p/a[contains(@class,'btn btn-default')]"));        
        assertEquals("Selenium Framework",element.getText());
        //by id
        element = driver.findElement(By.xpath("//form[@id='searchbox']"));        
        assertEquals("get",element.getAttribute("method"));        
        
        }   
}
