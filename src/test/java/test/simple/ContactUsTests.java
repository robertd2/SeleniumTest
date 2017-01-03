package test.simple;

import com.project.selenium.MainPage;
import com.project.selenium.Setup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class ContactUsTests {

    @BeforeClass
    public static void setUp() throws Exception {
        Setup.getDriver();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Setup.quit();
    }

    @Test
    public void testWarningMessages() throws Exception {
        MainPage mainPage=new MainPage();
        mainPage.clickContactUsBtn()
                .enterEmail("")
                .clickSendButton()
                .checkAlert("Invalid email address.")
                .clickContactUsBtn()
                .enterEmail("wrongAddress")
                .clickSendButton()
                .checkAlert("Invalid email address.")
                .clickContactUsBtn()
                .enterEmail("address@domain.com")
                .clickSendButton()
                .checkAlert("The message cannot be blank.")
                .enterMessage("New Message")
                .clickSendButton()
                .checkAlert("Please select a subject from the list provided.")
                .selectWebmasterSubject()
                .clickSendButton()
                .checkSuccessMessage("Your message has been successfully sent to our team.");
    }

    @Test
    public void testSubjectDescription(){
        MainPage mainPage=new MainPage();
        mainPage.clickContactUsBtn()
                .selectWebmasterSubject()
                .checkSubjectDescription("If a technical problem occurs on this website")
                .selectCustomerSubject()
                .checkSubjectDescription("For any question about a product, an order");
    }

    @Test
    public void testSendFile(){
            MainPage mainPage=new MainPage();
            File file = new File("./target/example.txt");
            String path = file.getAbsolutePath();
            mainPage.clickContactUsBtn()
                    .enterMessage("New Message")
                    .enterEmail("address@domain.com")
                    .selectWebmasterSubject()
                    .sendFile(path)
                    .clickSendButton()
                    .checkSuccessMessage("Your message has been successfully sent to our team.");
    }

    @Test
    public void testKeyboardNavigation(){
            MainPage mainPage=new MainPage();
            File file = new File("./target/example.txt");
            String path = file.getAbsolutePath();
            mainPage.clickContactUsBtn()
                    .selectSubjectAndPressTabKey()
                    .enterEmailAndPressTabKey("wrong mail")
                    .enterEmailAndPressTabKey("mail@domain.com")
                    .enterOrderReferenceAndPressTab("Order reference")
                    .sendFileAndPressTab(path)
                    .enterMessageAndPressTab("Message")
                    .clickSendButtonByKey()
                    .checkSuccessMessage("Your message has been successfully sent to our team.");
    }
}