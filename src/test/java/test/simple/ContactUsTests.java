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
        String invalidMailAlert = "Invalid email address.";
        String blankMessageAlert = "The message cannot be blank.";
        String emptyWebmasterSubject = "Please select a subject from the list provided.";
        String successMessage = "Your message has been successfully sent to our team.";

        MainPage mainPage=new MainPage();
        mainPage.clickContactUsBtn()
                .enterEmail("")
                .clickSendButton()
                .checkAlert(invalidMailAlert)
                .clickContactUsBtn()
                .enterEmail("wrongAddress")
                .clickSendButton()
                .checkAlert(invalidMailAlert)
                .clickContactUsBtn()
                .enterEmail("address@domain.com")
                .clickSendButton()
                .checkAlert(blankMessageAlert)
                .enterMessage("New Message")
                .clickSendButton()
                .checkAlert(emptyWebmasterSubject)
                .selectWebmasterSubject()
                .clickSendButton()
                .checkSuccessMessage(successMessage);
    }

    @Test
    public void testSubjectDescription(){
        String webMasterSubjectDescription = "If a technical problem occurs on this website";
        String customerSubjectDescription = "For any question about a product, an order";

        MainPage mainPage=new MainPage();
        mainPage.clickContactUsBtn()
                .selectWebmasterSubject()
                .checkSubjectDescription(webMasterSubjectDescription)
                .selectCustomerSubject()
                .checkSubjectDescription(customerSubjectDescription);
    }

    @Test
    public void testSendFile(){
        String successMessage = "Your message has been successfully sent to our team.";

        MainPage mainPage=new MainPage();
        File file = new File("./target/example.txt");
        String path = file.getAbsolutePath();
        mainPage.clickContactUsBtn()
                .enterMessage("New Message")
                .enterEmail("address@domain.com")
                .selectWebmasterSubject()
                .sendFile(path)
                .clickSendButton()
                .checkSuccessMessage(successMessage);
    }

    @Test
    public void testKeyboardNavigation(){
        String successMessage = "Your message has been successfully sent to our team.";

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
                .checkSuccessMessage(successMessage);
    }
}
