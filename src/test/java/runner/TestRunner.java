package runner;

import org.testng.annotations.DataProvider;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/featuresFiles",
        glue = {"stepDefinition"}
)
public class TestRunner {

}

		
		




