package test.cucumber;

import com.project.selenium.Setup;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "cucumber",
        glue={"test.cucumber"},
        format = {"pretty", "html:target/cucumber"}
)
public class RunCukesTest {
    @AfterClass
    public static void tearDown() throws Exception {
        Setup.quit();
    }
}
