package test.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "cucumber"
        ,glue={"test.cucumber"}
)
public class RunCukesTest {
}
